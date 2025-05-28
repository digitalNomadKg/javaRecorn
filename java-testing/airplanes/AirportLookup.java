import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class AirportLookup {
    private final Map<String, String> airportNames = new HashMap<>();
    private final Map<String, String> cityNames = new HashMap<>();

    public String getAirportName(String code) {
        return airportNames.getOrDefault(code, code);
    }

    public String getCityName(String code) {
        return cityNames.getOrDefault(code, code);
    }

    public static AirportLookup loadFromCsv(Path lookupPath) throws IOException {
        AirportLookup lookup = new AirportLookup();
        try (BufferedReader reader = Files.newBufferedReader(lookupPath)) {
            List<String> headers = CsvParser.parseLine(reader.readLine());
            int nameIdx = headers.indexOf("name");
            int cityIdx = headers.indexOf("municipality");
            int iataIdx = headers.indexOf("iata_code");
            int icaoIdx = headers.indexOf("icao_code");

            if (nameIdx == -1 || cityIdx == -1 || iataIdx == -1 || icaoIdx == -1) {
                throw new IOException(Colors.RED + "Airport lookup malformed");
            }

            String line;
            while ((line = reader.readLine()) != null) {
                List<String> fields = CsvParser.parseLine(line);
                if (fields.size() <= Math.max(Math.max(nameIdx, cityIdx), Math.max(iataIdx, icaoIdx))) continue;

                String name = fields.get(nameIdx).trim();
                String city = fields.get(cityIdx).trim();
                String iata = fields.get(iataIdx).trim();
                String icao = fields.get(icaoIdx).trim();

                if (!name.isEmpty() && !iata.isEmpty() && !icao.isEmpty()) {
                    lookup.airportNames.put("#" + iata, name);
                    lookup.airportNames.put("##" + icao, name);
                    lookup.cityNames.put("#" + iata, city);
                    lookup.cityNames.put("##" + icao, city);
                }
            }
        }
        return lookup;
    }
}