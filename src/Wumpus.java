public class Wumpus extends Creature {

    public Wumpus(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void act() {
        if (currentRoom.getNonPlayerNeighbors().size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getNonPlayerNeighbors()));
        } else if (currentRoom.getNonCommonNeighborsWithPlayer().size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getNonCommonNeighborsWithPlayer()));
        } else {
            moveToRoom(getRandomNeighbor());
        }
    }


}
