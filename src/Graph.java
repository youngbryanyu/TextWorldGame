import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void addNode(String name) {
        nodes.add(new Node(name));
    }

    public void addDirectedEdge(String name1, String name2) {
        Node node1 = getNode(name1);
        Node node2 = getNode(name2);
        node1.addNeighbor(node2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Node node1 = getNode(name1);
        Node node2 = getNode(name2);
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }

    public Node getNode(String name) {
        for (Node node : nodes) {
            if (node.getName().equalsIgnoreCase(name)) {
                return node;
            }
        }
        return null;
    }

    public class Node {
        private String name;
        private List<Node> neighbors;

        private Node(String name) {
            this.name = name;
            neighbors = new ArrayList<>();
        }

        private void addNeighbor(Node n) {
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
}
