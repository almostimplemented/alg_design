import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class Majority {
    public class Solver {
        private List<Integer> numIDs;

        public Solver(List<Integer> nums) {
            numIDs = nums;
        }

        public int solve() {
            return solveHelper(0, numIDs.size());
        }

        // Return majority candidate if exists, else return Integer.MIN_VALUE
        private int solveHelper(int begin, int end) {
            if (end - begin == 1) 
                return numIDs.get(begin);
            else {
                int candidateLeft  = solveHelper(begin, begin + (end - begin)/2);
                int candidateRight = solveHelper(begin + (end - begin)/2, end); 
                int tally = 0;
                // Check for majority :: O(n)
                if (candidateLeft != Integer.MIN_VALUE) 
                    for (int i = begin; i < end; i++) 
                        if (numIDs.get(i) == candidateLeft)
                            tally++;

                // There can only be one...
                if (tally > (end - begin)/2)
                    return candidateLeft;

                tally = 0;

                // Check for majority on right
                if (candidateRight != Integer.MIN_VALUE) 
                    for (int i = begin; i < end; i++) 
                        if (numIDs.get(i) == candidateRight)
                            tally++;

                if (tally > (end - begin)/2)
                    return candidateRight;

                return Integer.MIN_VALUE;
            }
        }
    }

    public static void main(String[] args) {
        Majority m = new Majority();
        Random rand = new Random();
        List<Integer> IDs = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++)
            IDs.add(rand.nextInt(3));
        Solver s = m.new Solver(IDs);
        boolean comma  = false;
        System.out.println("List:");
        for (int id : IDs) {
            if (comma) System.out.print(", ");
            System.out.print(id);
            comma = true;
        }
        System.out.println();
        System.out.println("Majority?");
        int winner = s.solve();
        if (winner == Integer.MIN_VALUE)
            System.out.println("No");
        else
            System.out.println("Yes: " + winner);
    }
}
