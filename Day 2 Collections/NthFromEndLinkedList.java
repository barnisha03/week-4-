import java.util.*;

public class NthFromEndLinkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));
        int n = 2;

        String result = findNthFromEnd(list, n);
        System.out.println("Nth element from the end: " + result);
    }

    public static String findNthFromEnd(LinkedList<String> list, int n) {
        Iterator<String> first = list.iterator();
        Iterator<String> second = list.iterator();

        // Move first pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (first.hasNext()) first.next();
            else return null; // n is larger than list size
        }

        // Move both pointers until first reaches the end
        while (first.hasNext()) {
            first.next();
            second.next();
        }

        return second.next(); // This is the Nth from the end
    }
}




