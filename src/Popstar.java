import java.util.ArrayList;
import java.util.HashMap;

public class Popstar extends Creature {
    private Player player;

    public Popstar(Level.Room currentRoom, Player player) {
        this.currentRoom = currentRoom;
        this.player = player;
    }

    public void act() {
        HashMap<String, Level.Room> neighbors = currentRoom.getNeighbors();
        if (neighbors.containsValue(player.getCurrentRoom())) {
            moveTowardsPlayer();
        } else {
            getRandomNeighbor();
        }
    }

    public Level.Room getRandomNeighbor() {
        return currentRoom.getRandomNeighbor();
    }

    public void moveTowardsPlayer() {
        ArrayList<Level.Room> nonPlayerNeighbors = currentRoom.getPlayerContainingNeighbors(player);
        int index = (int) (Math.random() * nonPlayerNeighbors.size());
        if (nonPlayerNeighbors.size() == 0) return;
        moveToRoom(nonPlayerNeighbors.get(index));
    }


}
