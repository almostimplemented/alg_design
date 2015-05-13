import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class NetForce {
    public class Solver {
        private List<Double> charges;

        public Solver(List<Double> q) {
            charges = q;
        }

        public double solve() {
            List<Double> distanceWeights = new ArrayList<Double>();
            int n = charges.size();
            for (int i = 1 - n; i < n; i++) {
                if (i == 0) {
                    distanceWeights.add(0.0);
                    continue;
                }
                double sign = -1.0;
                if (i > 0) sign = 1.0;
                distanceWeights.add(sign/(i*i));
            }
            // The solution is to compute the convolution between the vector of
            // charges and the vector distanceWeights. The resulting vector will
            // have F_j as a subvector.
            //
            // So... TO-DO: Implement FFT.
            return -1;
        }
    }

    public static void main(String[] args) {
        NetForce nf = new NetForce();
        List<Double> charges = new ArrayList<Double>();
        for (int i = 0; i < 10; i++)
            charges.add(1.0/(i+1) * 3);
        Solver s = nf.new Solver(charges);
        s.solve();
    }
}
