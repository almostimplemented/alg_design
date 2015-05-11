import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class SignificantInversions {
    public class Solver {
        private int count;
        private List<Integer> list;

        public Solver(List<Integer> l) {
            // Problem instance is a list of unique integers
            list = new ArrayList<Integer>(new LinkedHashSet<Integer>(l));
        }

        // return number of significant inversions
        public int solve() {
            count = 0;
            solveHelper(list);
            return count;
        }

        private List<Integer> solveHelper(List<Integer> l) {
            if (l.size() == 1) {
                return l;
            } else {
                return merge(solveHelper(l.subList(0, l.size()/2)),
                             solveHelper(l.subList(l.size()/2, l.size())));
            }
        }

        private List<Integer> merge(List<Integer> a, List<Integer> b) {
            List<Integer> l     = new ArrayList<Integer>();
            // We can appropriate the traditional inversion counting algorithm.
            //
            // In order to count inversions, we need to return a sorted list to the
            // next level up in the recursion tree. 
            //
            // So first we mergesort.
            int i = 0; 
            int j = 0;
            while (i < a.size() && j < b.size()) {
                if (a.get(i) < b.get(j)) {
                    l.add(a.get(i++));
                } else {
                    l.add(b.get(j++));
                }
            }
            while (i < a.size())
                l.add(a.get(i++));
            while (j < b.size())
                l.add(b.get(j++));

            // Now we do a second pass to compute the number of significant inversions. 
            // This is the same as performing the traditional inversion counting 
            // algorithm between a and 2*b
            i = 0; 
            j = 0;
            while (i < a.size() && j < b.size()) {
                if (a.get(i) > 2*b.get(j)) {
                    j++;
                    count += a.size() - i;
                } else {
                    i++;
                }
            }

            // Send the sorted list up a level in the recursion
            return l;
        }
    }

    public static void main(String[] args) {
        SignificantInversions si = new SignificantInversions();
        Random rand = new Random();
        List<Integer> l = new ArrayList<Integer>();
        int max = 100;
        int min = 0;
        for (int i = 0; i < 10; i++) {
            int r = rand.nextInt((max - min) + 1) + min;
            while (l.contains(r))
                r = rand.nextInt((max - min) + 1) + min;
            l.add(r);
        }
        boolean comma  = false;
        System.out.println("List:");
        for (int v : l) {
            if (comma) System.out.print(", ");
            System.out.print(v);
            comma = true;
        }
        System.out.println();
        Solver s = si.new Solver(l);
        System.out.println("The number of inverions is " + s.solve());
    }
}
