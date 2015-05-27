import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
public class CountInversionsTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        for (int N = 1; N <= 20; N++) {
            System.out.println("N = " + N);
            list.clear();
            for (int i = 0; i < N; i++) {
                list.add(i);
            }
            float movingAverage = 0;
            for (int iter = 1; iter <= 10000; iter++) {
                Collections.shuffle(list);
                int inversions = CountInversions.count(list);
                movingAverage = (inversions + movingAverage * (iter-1))/(iter);
            }
            System.out.println("E[I] = " + movingAverage);
        }
    }
}
