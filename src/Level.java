import java.util.ArrayList;
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

    public Room getRandomRoom() {
        ArrayList<Level.Room> roomList = new ArrayList<>(rooms.values());
        int index = (int) (Math.random() * roomList.size());
        return roomList.get(index);
    }

    public ArrayList<Room> getRoomList() {
        return new ArrayList<>(rooms.values());
    }


    public class Room {
        private String name, description;
        private HashMap<String, Room> neighbors;
        private ArrayList<Item> items;
        private ArrayList<Creature> creatures;

        private Room(String name, String description) {
            this.name = name;
            this.description = description;
            neighbors = new HashMap<>();
            items = new ArrayList<>();
            creatures = new ArrayList<>();
        }

        public void addNeighbor(Room n) {
            neighbors.put(n.getName(), n);
        }

        public String getNeighborNames() {
            String names = "";
            for (Room n : neighbors.values()) {
                names += "-> " + n.getName() + " >> " + n.getDescription() + "\n";
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

        public ArrayList<Item> getItems() {
            return items;
        }

        public String displayItems() {
            String items = "";
            for (Item item : this.items) {
                items += "-> " + item.getName() + " >> " + item.getDescription() + "\n";
            }
            if (items.length() > 1) return items.substring(0, items.length() - 1);
            return items;
        }

        public void addItem(String name) {
            items.add(new Item(name, ""));
        }

        public void addItem(String name, String description) {
            items.add(new Item(name, description));
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public Item removeItem(String name) {
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    items.remove(item);
                    return item;
                }
            }
            return null;
        }

        public boolean destroyItem(String name) {
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    return items.remove(item);
                }
            }
            return false;
        }

        public HashMap<String, Room> getNeighbors() {
            return neighbors;
        }

        public void createCreature(Creature c) {
            creatures.add(c);
        }

        public ArrayList<Creature> getCreatures() {
            return creatures;
        }

        public void removeCreature(Creature creature) {
            creatures.remove(creature);
        }

        public ArrayList<Room> getNonPlayerNeighbors(Player player) {
            ArrayList<Room> neighbors = new ArrayList<>(getNeighbors().values());
            ArrayList<Room> nonPlayerNeighbors = new ArrayList<>();
            for (Room room : neighbors) {
                if (!room.containsPlayer(room, player)){
                    nonPlayerNeighbors.add(room);
                }
            }
            return nonPlayerNeighbors;
        }

        public ArrayList<Room> getPlayerContainingNeighbors(Player player) {
            ArrayList<Room> neighbors = new ArrayList<>(getNeighbors().values());
            ArrayList<Room> nonPlayerNeighbors = new ArrayList<>();
            for (Room room : neighbors) {
                if (room.containsPlayer(room, player)){
                    nonPlayerNeighbors.add(room);
                }
            }
            return nonPlayerNeighbors;
        }

        public boolean containsPlayer(Room room, Player player) {
            if (player.getCurrentRoom().getName().equals(room.getName())) {
                return true;
            }
            return false;
        }

    }
}
