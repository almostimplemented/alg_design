import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class LocalMin {
    public class Solver {
        Node<Double> root; 
        public Solver(Node<Double> n) {
            root = n;
        }

        public double solve() {
            Node<Double> n = root;
            List<Node<Double>> children = n.getNeighbors();
            while (!children.isEmpty()) {
                Node<Double> left = children.get(0);
                Node<Double> right = children.get(1);
                if (left.getData() > n.getData() && right.getData() > n.getData())
                    break;
                // Else, check left's children
                n = left;
                children = n.getNeighbors();
            }
            return n.getData();
        }
    }

    public static void main(String[] args) {
        // Build a simple binary tree
        // Sorry for clunky code
        List<Node<Double>> empty = new ArrayList<Node<Double>>();
        Node<Double> n3 = new Node<Double>(11.3, empty);
        Node<Double> n4 = new Node<Double>(11.1, empty);
        Node<Double> n5 = new Node<Double>(13.9, empty);
        Node<Double> n6 = new Node<Double>(9.0, empty);
        List<Node<Double>> rootList = new ArrayList<Node<Double>>();
        List<Node<Double>> n1List = new ArrayList<Node<Double>>();
        List<Node<Double>> n2List = new ArrayList<Node<Double>>();
        Node<Double> root = new Node<Double>(11.0, rootList);
        Node<Double> n1 = new Node<Double>(10.0, n1List);
        Node<Double> n2 = new Node<Double>(12.0, n2List);
        root.addEdgeTo(n1);
        root.addEdgeTo(n2);
        n1.addEdgeTo(n3);
        n1.addEdgeTo(n4);
        n2.addEdgeTo(n5);
        n2.addEdgeTo(n6);

        LocalMin lm = new LocalMin();
        Solver s = lm.new Solver(root);
        System.out.println("Local min: " + s.solve());

    }
}
