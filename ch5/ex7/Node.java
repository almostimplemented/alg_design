import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T data;
    private List<Node<T>> neighbors;
    public Node(T initialData) {
        data = initialData;
        neighbors = new ArrayList<Node<T>>();
    }
    public Node(T initialData, List<Node<T>> initialNeighbors) {
        data = initialData;
        neighbors = initialNeighbors;
    }
    public T getData() {
        return data;
    }
    public List<Node<T>> getNeighbors() {
        return neighbors;
    }
    public boolean isAdjacentTo(Node<T> n) {
        return neighbors.contains(n);
    }

    public void addEdgeTo(Node<T> n) {
        neighbors.add(n);
    }
}
