import java.util.Scanner;

public class MultipleCatchExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // You can set array to null to simulate NullPointerException
        int[] numbers = {10, 20, 30, 40, 50};
        // int[] numbers = null; // Uncomment to test NullPointerException

        try {
            System.out.print("Enter index to retrieve value: ");
            int index = scanner.nextInt();

            int value = numbers[index]; // May throw exceptions
            System.out.println("Value at index " + index + ": " + value);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index!");
        } catch (NullPointerException e) {
            System.out.println("Array is not initialized!");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}














