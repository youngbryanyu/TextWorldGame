import java.util.HashMap;

public class Graph {

    private HashMap<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public void addNode(String name, String description) {
        nodes.put(name, new Node(name, description));
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
        return nodes.get(name);
    }

    public class Node {
        private String name;
        private String description;
        private HashMap<String, Node> neighbors;

        private Node(String name, String description) {
            this.name = name;
            this.description = description;
            neighbors = new HashMap<>();
        }

        private void addNeighbor(Node n) {
            neighbors.put(n.getName(), n);
        }

        public String getNeighborNames() {
            String names = "";
            for (Node n : neighbors.values()) {
                names += "-> "+ n.getName() + " >> " + n.getDescription() + "\n";
            }
            if (names.length() > 2) return names.substring(0, names.length() - 1);
            return "";
        }

        public Node getNeighbor(String name) {
            return neighbors.get(name);
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
