import java.util.Scanner;

// Step 1: Define a custom exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CustomExceptionExample {

    // Step 2: Method that validates age
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
    }

    // Step 3: Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Get user input
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            // Call validation method
            validateAge(age);

            // If no exception, access is granted
            System.out.println("Access granted!");

        } catch (InvalidAgeException e) {
            // Handle custom exception
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions (e.g., InputMismatchException)
            System.out.println("Invalid input. Please enter a numeric age.");
        } finally {
            scanner.close();
        }
    }
}













