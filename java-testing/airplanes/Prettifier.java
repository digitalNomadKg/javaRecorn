import java.io.IOException;
import java.nio.file.*;

public class Prettifier {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println(Colors.YELLOW+ "Usage: " + Colors.GREEN+"java Prettifier.java ./input.txt ./output.txt ./airport-lookup.csv");
            System.exit(1);
        }

        Path inputPath = Paths.get(args[0]);
        Path outputPath = Paths.get(args[1]);
        Path lookupPath = Paths.get(args[2]);

        if (!Files.exists(inputPath)) {
            System.err.println("\n"+Colors.RED + "Note: " + Colors.YELLOW + "Input not found.");
            System.exit(1);
        }

        if (!Files.exists(lookupPath)) { // check if lookup CSV exists
            System.out.println("\n"+Colors.RED + "Airport lookup not found"); // print lookup missing error
            return; // exit on error
        }

        try {
            AirportLookup lookup = AirportLookup.loadFromCsv(lookupPath);
            String input = Files.readString(inputPath);
            String result = new ItineraryProcessor(lookup).process(input);

            Files.writeString(outputPath, result);
            System.out.println(Colors.YELLOW + "\nPrettified Itinerary:");
            System.out.println(Colors.GREEN + result + Colors.RESET);
            System.out.println(Colors.YELLOW + "\nOutput saved to: " + Colors.CYAN + outputPath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(2);
        }
    }

}
