
import java.io.File;
import java.io.IOException;

public class CreateFile {

    public static void main(String[] args) {
        try {
            File fileName = new File("/Users/drc/GitHub/java/javaRecornjava-testing/hello.txt");
            if (fileName.createNewFile()) {
                System.out.println("File Created a file: " + fileName.getName());
            } else {
                System.out.println("There is no file: " + fileName.getName());
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
