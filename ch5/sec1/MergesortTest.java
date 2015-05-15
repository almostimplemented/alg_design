import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class MergesortTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        Random rand = new Random();

        int min = 0;
        int max = 20;

        for (int i = 0; i < 7; i++) {
            int r;
            do {
                r = rand.nextInt((max - min) + 1) + min;
            } while (list.contains(r));
            list.add(r);
        }
        boolean comma  = false;
        System.out.println("Unsorted List:");
        for (int v : list) {
            if (comma) System.out.print(", ");
            System.out.print(v);
            comma = true;
        }
        System.out.println();
        comma = false;
        Mergesort.sort(list);
        System.out.println("Sorted List:");
        for (int v : list) {
            if (comma) System.out.print(", ");
            System.out.print(v);
            comma = true;
        }
        System.out.println();
    }
}
