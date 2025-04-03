
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {

    public static void main(String[] args) {

        try {
            File myFile = new File("/Users/drc/GitHub/java/javaRecorn/java-testing/hello1.txt");
            Scanner readScanner = new Scanner(myFile);

            while (readScanner.hasNextLine()) {
                String data = readScanner.nextLine();
                System.out.println(data);
            }
            readScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occured");
            e.printStackTrace();

        }

    }
}
