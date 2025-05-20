import java.util.*;

public class RemoveDuplicatesPreserveOrder {
    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(3, 1, 2, 2, 3, 4);
        List<Integer> result = removeDuplicates(input);
        System.out.println("List after removing duplicates: " + result);
    }

    public static List<Integer> removeDuplicates(List<Integer> list) {
        Set<Integer> seen = new LinkedHashSet<>();
        seen.addAll(list);
        return new ArrayList<>(seen);
    }
}




