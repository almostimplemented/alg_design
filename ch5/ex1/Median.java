import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Collections;

public class Median {
    // simple "database" object
    public class Database {
        List<Integer> data;
        public Database(List<Integer> numbers) {
            data = new ArrayList<Integer>(new LinkedHashSet<Integer>(numbers));
            Collections.sort(data);
        }
        public int size() {
            return data.size();
        }
        public int query(int k) {
            try {
                return data.get(k);
            } catch (IndexOutOfBoundsException e) {
                System.err.println("IndexOutOfBoundsException: " + e.getMessage());
                return Integer.MIN_VALUE;
            }
        }
    }

    // problem input are two databases
    public class ProblemInstance {
        private Database a;
        private Database b;
        public ProblemInstance(Database d1, Database d2) {
            a = d1;
            b = d2;
        }
    }

    public class Solver {
        private ProblemInstance inst;
        private Database db1;
        private Database db2;

        public Solver(ProblemInstance instance) {
            inst = instance;
            db1 = inst.a;
            db2 = inst.b;
        }
        // Return the n^th largest element in union of databases
        public int solve() {
            // Call auxiliary recursive divide and conquer method
            if (db1.size() != db2.size()) {
                System.err.println("Error: databases must be of equal size");
                return Integer.MIN_VALUE;
            }
            return solveHelper(0, 0, db1.size());
        }

        private int solveHelper(int a, int b, int n) {
            if (n == 1) {
                return Math.min(db1.query(a), db2.query(b));
            } else if (n == 2) {
                return Math.min(Math.max(db1.query(a), db2.query(b)), 
                                Math.min(db1.query(a+1), db2.query(b+1)));
            } else {
                // Get the median of each list
                int x = db1.query(a + n/2);
                int y = db2.query(b + n/2);
                // Note the following: if x < y, then any element greater
                // than y is too big to be the median of the combined lists.
                // Similarly, the first floor(n/2) elements of the second list
                // are too small. (A symmetric argument holds for x > y.)
                if (x < y) {
                    if (n % 2 == 0)
                        return solveHelper(a + n/2 - 1, b, n/2 + 1);
                    else
                        return solveHelper(a + n/2, b, n/2 + 1);
                } else {
                    if (n % 2 == 0)
                        return solveHelper(a, b + n/2 - 1, n/2 + 1);
                    else
                        return solveHelper(a, b + n/2, n/2 + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Median m = new Median();

        List<Integer> l  = new ArrayList<Integer>();
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        int max = 100;
        int min = -100;

        // Generate random problem instances of varying sizes,
        // verify that returned element is equal to the n^th
        // smallest element of the combined lists
        for (int size = 1; size < 20; size++) {
            l1.clear();
            l2.clear();
            l.clear();
            for (int i = 0; i < size; i++) {
                int r1 = rand.nextInt((max - min) + 1) + min;
                int r2 = rand.nextInt((max - min) + 1) + min;

                // Ensure uniqueness (problem assumption)
                while (l1.contains(r1)) 
                    r1 = rand.nextInt((max - min) + 1) + min;
                while (l2.contains(r2)) 
                    r2 = rand.nextInt((max - min) + 1) + min;

                l1.add(r1);
                l2.add(r2);
                l.add(r1); l.add(r2);
            }
            Collections.sort(l);
            Database d1 = m.new Database(l1);
            Database d2 = m.new Database(l2);
            ProblemInstance inst = m.new ProblemInstance(d1, d2);
            Solver solver = m.new Solver(inst);
            System.out.format("%+3d =?= %+3d%n", solver.solve(), l.get(size - 1));
        }
    }
}
