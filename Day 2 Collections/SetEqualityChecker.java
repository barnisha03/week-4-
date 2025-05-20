import java.util.*;

public class SetEqualityChecker {
    public static void main(String[] args) {
        // Define two sets
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 2, 1));

        // Check if sets are equal
        boolean areEqual = set1.equals(set2);

        // Print result
        System.out.println("Are sets equal? " + areEqual);
    }
}


