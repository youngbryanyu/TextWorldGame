import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Node> neighbors;

    public Node(String name) {
        this.name = name;
        neighbors = new ArrayList<>();
    }

    public void addNeighbor(Node n) {
        neighbors.add(n);
    }

    public String getNeighborNames() {
        String names = "";
        for (Node n : neighbors) {
            names += n.getName() + ", ";
        }
        return names.substring(0, names.length() - 2);
    }

    public Node getNeighbor(String name) {
        for (Node n : neighbors) {
            if (n.getName().equalsIgnoreCase(name))
                return n;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
