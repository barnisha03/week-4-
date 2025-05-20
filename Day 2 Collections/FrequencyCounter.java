import java.util.*;

public class FrequencyCounter {
    public static void main(String[] args) {
        // Sample input list
        List<String> fruits = Arrays.asList("apple", "banana", "apple", "orange");

        // Call the method to count frequency
        Map<String, Integer> frequencyMap = countFrequency(fruits);

        // Print the result
        System.out.println(frequencyMap);
    }

    public static Map<String, Integer> countFrequency(List<String> list) {
        Map<String, Integer> map = new HashMap<>();

        for (String item : list) {
            if (map.containsKey(item)) {
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        }

        return map;
    }
}


