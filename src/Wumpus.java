import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus extends Creature {
    private Player player;

    public Wumpus(Level.Room currentRoom, Player player) {
        this.currentRoom = currentRoom;
        this.player = player;
    }

    public void move() {
        HashMap<String, Level.Room> neighbors = currentRoom.getNeighbors();
        if (neighbors.containsValue(player.getCurrentRoom())) {
            moveAwayFromPlayer();
        } else {
            moveRandom();
        }
    }

    public void moveRandom() {
        HashMap<String, Level.Room> neighbors = currentRoom.getNeighbors();
        ArrayList<Level.Room> neighborList = new ArrayList<>(neighbors.values());
        int index = (int) (Math.random() * neighborList.size());
        if (neighborList.size() == 0) return;
        setRoom(neighborList.get(index));
    }

    public void moveAwayFromPlayer() {
        ArrayList<Level.Room> nonPlayerNeighbors = currentRoom.getNonPlayerNeighbors(player);
        int index = (int) (Math.random() * nonPlayerNeighbors.size());
        if (nonPlayerNeighbors.size() == 0) return;
        setRoom(nonPlayerNeighbors.get(index));
    }
}
