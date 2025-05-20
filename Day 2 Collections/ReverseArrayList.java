import java.util.ArrayList;
import java.util.List;

public class ReverseArrayList {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        // Sample input: [1, 2, 3, 4, 5]
        for (int i = 1; i <= 5; i++) {
            arrayList.add(i);
        }

        System.out.println("Original ArrayList: " + arrayList);

        // Manual reverse
        int left = 0;
        int right = arrayList.size() - 1;

        while (left < right) {
            // Swap elements
            int temp = arrayList.get(left);
            arrayList.set(left, arrayList.get(right));
            arrayList.set(right, temp);

            left++;
            right--;
        }

        System.out.println("Reversed ArrayList: " + arrayList);
    }
}
