public class Popstar extends Creature {

    public Popstar(Level.Room currentRoom, Player player) {
        this.currentRoom = currentRoom;
    }

    public void act() {
        if (currentRoom.getPlayerContainingNeighbors().size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getPlayerContainingNeighbors()));
        } else if (currentRoom.getCommonNeighborsWithPlayer().size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getCommonNeighborsWithPlayer()));
        } else {
            moveToRoom(getRandomNeighbor());
        }
    }
}
