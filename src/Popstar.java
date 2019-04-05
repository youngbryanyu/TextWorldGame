import java.util.ArrayList;
import java.util.HashMap;

public class Popstar extends Creature{
    private Player player;

    public Popstar(Level.Room currentRoom, Player player) {
        this.currentRoom = currentRoom;
        this.player = player;
    }

    public void move() {
        HashMap<String, Level.Room> neighbors = currentRoom.getNeighbors();
        if (neighbors.containsValue(player.getCurrentRoom())) {
            moveTowardsPlayer();
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

   public void moveTowardsPlayer(){
       ArrayList<Level.Room> nonPlayerNeighbors = currentRoom.getPlayerContainingNeighbors(player);
       int index = (int) (Math.random() * nonPlayerNeighbors.size());
       if (nonPlayerNeighbors.size() == 0) return;
       setRoom(nonPlayerNeighbors.get(index));
   }


}
