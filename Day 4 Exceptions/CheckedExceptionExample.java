import java.io.*;

public class CheckedExceptionExample {
    public static void main(String[] args) {
        FileInputStream fis = null;

        try {
            // Attempt to open the file
            fis = new FileInputStream("data.txt");

            // Read and print file content
            int content;
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }

        } catch (IOException e) {
            // Handle if file is not found or cannot be read
            System.out.println("File not found");
        } finally {
            // Close the file input stream if it was opened
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file.");
            }
        }
    }
}













