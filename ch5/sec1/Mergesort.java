import java.util.List;
import java.util.ArrayList;

public class Mergesort {
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        int n = list.size();
        List<T> sortedList = mergesort(list);
        list.clear();
        list.addAll(sortedList);
    }

    private static <T extends Comparable<? super T>> List<T> mergesort(List<T> list) {
        int n = list.size();
        if (n == 1)
            return list;

        List<T> firstHalf = list.subList(0, n/2);
        List<T> secondHalf = list.subList(n/2, n);
        return merge(mergesort(firstHalf), mergesort(secondHalf));
    }

    private static <T extends Comparable<? super T>> List<T> merge(List<T> firstHalf, List<T> secondHalf) {
        int i = 0;
        int j = 0;

        List<T> result = new ArrayList<T>();

        while (i < firstHalf.size() && j < secondHalf.size()) {
            T first  = firstHalf.get(i);
            T second = secondHalf.get(j);
            if (first.compareTo(second) < 0) {
                result.add(first);
                i++;
            } else {
                result.add(second);
                j++;
            }
        }

        while (i < firstHalf.size()) 
            result.add(firstHalf.get(i++));
        while (j < secondHalf.size()) 
            result.add(secondHalf.get(j++));

        return result;
    }
}
