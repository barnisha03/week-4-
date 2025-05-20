import java.util.*;

public class SetOperations {
    public static void main(String[] args) {
        // Input sets
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5));

        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);  // Adds elements from set2 that aren't already in set1

        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);  // Keeps only elements also in set2

        // Output results
        System.out.println("Union: " + union);
        System.out.println("Intersection: " + intersection);
    }
}


