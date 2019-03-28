import java.util.HashMap;

public class Level {

    private HashMap<String, Room> rooms;

    public Level() {
        rooms = new HashMap<>();
    }

    public void addRoom(String name, String description) {
        rooms.put(name, new Room(name, description));
    }

    public void addDirectedEdge(String name1, String name2) {
        Room room1 = getRoom(name1);
        Room room2 = getRoom(name2);
        room1.addNeighbor(room2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room room1 = getRoom(name1);
        Room room2 = getRoom(name2);
        room1.addNeighbor(room2);
        room2.addNeighbor(room1);
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public class Room {
        private String name, description;
        private HashMap<String, Room> neighbors;

        private Room(String name, String description) {
            this.name = name;
            this.description = description;
            neighbors = new HashMap<>();
        }

        private void addNeighbor(Room n) {
            neighbors.put(n.getName(), n);
        }

        public String getNeighborNames() {
            String names = "";
            for (Room n : neighbors.values()) {
                names += "-> "+ n.getName() + " >> " + n.getDescription() + "\n";
            }
            if (names.length() > 1) return names.substring(0, names.length() - 1);
            return "";
        }

        public Room getNeighbor(String name) {
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
