import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReading {
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }


}
