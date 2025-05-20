import java.util.*;

public class ListRotation {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        int rotateBy = 2;

        List<Integer> rotated = rotateList(list, rotateBy);
        System.out.println("Rotated List: " + rotated);
    }

    public static List<Integer> rotateList(List<Integer> list, int k) {
        int size = list.size();
        k = k % size; // handle rotation greater than size
        List<Integer> result = new ArrayList<>();
        result.addAll(list.subList(k, size));
        result.addAll(list.subList(0, k));
        return result;
    }
}



