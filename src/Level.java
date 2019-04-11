import java.util.ArrayList;
import java.util.HashMap;

public class Level {

    private HashMap<String, Room> rooms;
    private Player player;

    public Level(Player player) {
        rooms = new HashMap<>();
        this.player = player;
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


    public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    public void createRandomChickens(int numChickens) {
        for (int i = 0; i < numChickens; i++) {
            Room room = getRandomRoom();
            createChicken(room);
        }
    }

    public void createChicken(Room room) {
        room.createCreature(new Chicken(room));
    }


    public void createPopStar(Room room) {
        room.createCreature(new Popstar(room, player));
    }

    public void createWumpus(Room room) {
        room.createCreature(new Wumpus(room, player));
    }

    public void createRandomCreatures(int numCreatures) {
        for (int i = 0; i < numCreatures; i++) {
            Level.Room room = getRandomRoom();
            int num = (int) (Math.random() * 3);
            if (num == 0)
                createChicken(room);
            else if (num == 1)
                createWumpus(room);
            else
                createPopStar(room);
        }
    }


    public void updateAllCreatures() {
        for (Level.Room room : getRooms()) {
            ArrayList<Creature> creatures = room.getCreatures();
            for (Creature c : creatures) {
                c.act();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public class Room {
        private String name, description;
        private HashMap<String, Room> neighbors;
        private ArrayList<Item> items;
        private ArrayList<Creature> creatures;
        private int numChickens, numPopstars, numWumpuses;

        private Room(String name, String description) {
            this.name = name;
            this.description = description;
            neighbors = new HashMap<>();
            items = new ArrayList<>();
            creatures = new ArrayList<>();
            numChickens = 0;
            numPopstars = 0;
            numWumpuses = 0;
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

        public void displayNeighbors() {
            System.out.println(getNeighborNames());
        }

        public void displayCreatures() {
            System.out.println();
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

        public ArrayList<Creature> getCreatures() {
            return creatures;
        }

        public void removeCreature(Creature c) {
            creatures.remove(c);
            updateCreatureCount(c);
        }

        public Level.Room getRandomNeighbor() {
            ArrayList<Level.Room> neighborList = new ArrayList<>(getNeighbors().values());
            int index = (int) (Math.random() * neighborList.size());
            if (neighborList.size() == 0) return null;
            return neighborList.get(index);
        }

        public Room getRandomNeighbor(ArrayList<Room> specificNeighbors) {
            int index = (int) (Math.random() * specificNeighbors.size());
            if (specificNeighbors.size() == 0) return null;
            return specificNeighbors.get(index);
        }

        public ArrayList<Room> getNonPlayerNeighbors() {
            ArrayList<Room> neighbors = new ArrayList<>(getNeighbors().values());
            ArrayList<Room> nonPlayerNeighbors = new ArrayList<>();
            for (Room room : neighbors) {
                if (!room.containsPlayer(room, player)) {
                    nonPlayerNeighbors.add(room);
                }
            }
            return nonPlayerNeighbors;
        }

        public ArrayList<Room> getPlayerContainingNeighbors() {
            ArrayList<Room> neighbors = new ArrayList<>(getNeighbors().values());
            ArrayList<Room> playerContainingNeighbors = new ArrayList<>();
            for (Room room : neighbors) {
                if (room.containsPlayer(room, player)) {
                    playerContainingNeighbors.add(room);
                }
            }
            return playerContainingNeighbors;
        }

        public boolean containsPlayer(Room room, Player player) {
            if (player.getCurrentRoom().getName().equals(room.getName())) {
                return true;
            }
            return false;
        }

        public ArrayList<Room> getCommonNeighborsWithPlayer() {
            ArrayList<Room> commonNeighbors = new ArrayList<>();
            ArrayList<Room> currentNeighbors = new ArrayList<>(getNeighbors().values());
            ArrayList<Room> playerNeighbors = new ArrayList<>(player.getCurrentRoom().getNeighbors().values());
            for (Room room1 : currentNeighbors) {
                for (Room room2 : playerNeighbors) {
                    if (room1.equals(room2)) {
                        commonNeighbors.add(room1);
                    }
                }
            }
            return commonNeighbors;
        }

        public ArrayList<Room> getNonCommonNeighborsWithPlayer() {
            ArrayList<Room> nonCommonNeighbors = new ArrayList<>();
            ArrayList<Room> currentNeighbors = new ArrayList<>(getNeighbors().values());
            ArrayList<Room> playerNeighbors = new ArrayList<>(player.getCurrentRoom().getNeighbors().values());
            for (Room room1 : currentNeighbors) {
                for (Room room2 : playerNeighbors) {
                    if (!room1.equals(room2)) {
                        nonCommonNeighbors.add(room1);
                    }
                }
            }
            return nonCommonNeighbors;
        }

        public void createCreature(Creature c) {
            creatures.add(c);
            updateCreatureCount(c);
        }

        private void updateCreatureCount(Creature c) {
            if (c instanceof Chicken) {
                numChickens++;
            } else if (c instanceof Wumpus) {
                numWumpuses++;
            } else if (c instanceof Popstar) {
                numPopstars++;
            }
        }
    }
}
