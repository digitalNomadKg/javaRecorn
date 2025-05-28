import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItineraryProcessor {
    private static final Pattern DATETIME = Pattern.compile("(D|T12|T24)\\(([^)]+)\\)");
    private static final Pattern CITY = Pattern.compile("\\*(##?[A-Z]{3,4})");
    private static final Pattern AIRPORT = Pattern.compile("(##?[A-Z]{3,4})");

    private final AirportLookup lookup;

    public ItineraryProcessor(AirportLookup lookup) {
        this.lookup = lookup;
    }

    public String process(String text) {
        return collapseBlankLines(
                replaceAirportCodes(
                        formatDateTimes(
                                normalizeWhitespace(text)
                        )
                )
        ).strip();
    }

    private String normalizeWhitespace(String text) {
        // Replace various control characters (like carriage returns, form feeds, vertical tabs) with newline
        text = text.replaceAll("[\\r\\f\\v\\u000B]+", "\n");
        // Collapse all consecutive whitespace (including tabs, multiple spaces, etc.) into a single space
        text = text.replaceAll("\\s{2,}", " ");
        return text;
    }

    private String formatDateTimes(String text) {
        Matcher m = DATETIME.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, formatSingleDateTime(m.group(1), m.group(2)));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private String formatSingleDateTime(String type, String raw) {
        try {
            OffsetDateTime dt = OffsetDateTime.parse(raw.replace(' ', '-'), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return switch (type) {
                case "D" -> dt.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
                case "T12" -> dt.format(DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH)) + " (" + dt.getOffset() + ")";
                case "T24" -> dt.format(DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)) + " (" + dt.getOffset() + ")";
                default -> type + "(" + raw + ")";
            };
        } catch (DateTimeParseException e) {
            System.err.println("Warning: malformed date/time: " + type + "(" + raw + ")");
            return type + "(" + raw + ")";
        }
    }

    private String replaceAirportCodes(String text) {
        Matcher cityMatcher = CITY.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (cityMatcher.find()) {
            cityMatcher.appendReplacement(sb, lookup.getCityName(cityMatcher.group(1)));
        }
        cityMatcher.appendTail(sb);

        Matcher airportMatcher = AIRPORT.matcher(sb.toString());
        sb = new StringBuffer();
        while (airportMatcher.find()) {
            airportMatcher.appendReplacement(sb, lookup.getAirportName(airportMatcher.group()));
        }
        airportMatcher.appendTail(sb);

        return sb.toString();
    }

    private static String collapseBlankLines(String text) { // limit empty lines
        text = text.replaceAll("(?m)\\n{3,}", "\n\n"); // 3+ newlines → 2
        return text.strip(); // trim leading/trailing blanks
    }
}