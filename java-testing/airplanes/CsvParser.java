import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public static List<String> parseLine(String line) {
        List<String> fields = new ArrayList<>();
        if (line == null) return fields;

        boolean quoted = false;
        StringBuilder sb = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                quoted = !quoted;
            } else if (c == ',' && !quoted) {
                fields.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        fields.add(sb.toString());
        return fields;
    }
}