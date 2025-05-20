import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        String sourcePath = "source.txt";       // Source file path
        String destinationPath = "destination.txt"; // Destination file path

        File inputFile = new File(sourcePath);
        File outputFile = new File(destinationPath);

        // Check if the source file exists
        if (!inputFile.exists()) {
            System.out.println("Source file does not exist.");
            return;
        }

        // Perform file copy
        try (
            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile)
        ) {
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }

            System.out.println("File copied successfully from \"" + sourcePath + "\" to \"" + destinationPath + "\".");

        } catch (IOException e) {
            System.out.println("An error occurred during file handling: " + e.getMessage());
        }
    }
}






