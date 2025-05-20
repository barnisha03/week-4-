import java.io.*;

public class BufferedFileCopy {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("largeFile.dat");
        File outputBuffered = new File("copyBuffered.dat");
        File outputUnbuffered = new File("copyUnbuffered.dat");

        // Buffered copy
        long startBuffered = System.nanoTime();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputBuffered))) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
        long endBuffered = System.nanoTime();
        System.out.println("Buffered Time: " + (endBuffered - startBuffered) + " ns");

        // Unbuffered copy
        long startUnbuffered = System.nanoTime();
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputUnbuffered)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        long endUnbuffered = System.nanoTime();
        System.out.println("Unbuffered Time: " + (endUnbuffered - startUnbuffered) + " ns");
    }
}







