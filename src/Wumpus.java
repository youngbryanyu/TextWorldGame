public class Wumpus extends Creature {
    private Player player;

    public Wumpus(Level.Room currentRoom, Player player) {
        this.currentRoom = currentRoom;
        this.player = player;
    }

    public void act() {
        // TODO: find neighbors
        if (currentRoom.getNonPlayerNeighbors(player).size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getNonPlayerNeighbors(player)));
        } else if (currentRoom.getNonCommonNeighborsWithPlayer(player).size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getNonCommonNeighborsWithPlayer(player)));
        } else {
            moveToRoom(getRandomNeighbor());
        }
    }


}
