import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFirstLine {
    public static void main(String[] args) {
        String fileName = "info.txt"; // File to read

        // Try-with-resources ensures BufferedReader is automatically closed
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String firstLine = br.readLine();

            if (firstLine != null) {
                System.out.println("First line: " + firstLine);
            } else {
                System.out.println("The file is empty.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}














