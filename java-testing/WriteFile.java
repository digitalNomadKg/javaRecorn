
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static void main(String[] args) {

        try {

            File file1 = new File("/Users/drc/GitHub/java/javaRecorn/java-testing/hello1.txt");
            if (file1.exists()) {
                System.out.println("There is such file");
            } else {
                file1.createNewFile();
                System.out.println("File created");
            }

            try (FileWriter updateFile = new FileWriter("/Users/drc/GitHub/java/javaRecorn/java-testing/hello1.txt")) {

                updateFile.write("Hello world. ");
                updateFile.write("Java Files are realy tricky. ");
                updateFile.write("Not that much believe me");

            }
    
            System.out.println("Text successfully added");

        } catch (IOException e) {
            System.out.println("An error occured");
        }

    }

}
