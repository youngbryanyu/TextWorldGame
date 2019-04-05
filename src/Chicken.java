import java.util.ArrayList;
import java.util.HashMap;

public class Chicken extends Creature {

    private Level.Room currentRoom;

    public Chicken(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void move() {
        moveRandom();
    }

    public void moveRandom() {
        HashMap<String, Level.Room> neighbors = currentRoom.getNeighbors();
        ArrayList<Level.Room> neighborList = new ArrayList<>(neighbors.values());
        int index = (int) (Math.random() * neighborList.size());
        if (neighborList.size() == 0) return;
        this.currentRoom = neighborList.get(index);
    }
}
