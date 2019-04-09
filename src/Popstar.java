public class Popstar extends Creature {
    private Player player;

    public Popstar(Level.Room currentRoom, Player player) {
        this.currentRoom = currentRoom;
        this.player = player;
    }

    public void act() {
        if (currentRoom.getPlayerContainingNeighbors(player).size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getPlayerContainingNeighbors(player)));
        } else if (currentRoom.getCommonNeighborsWithPlayer(player).size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getCommonNeighborsWithPlayer(player)));
        } else {
            moveToRoom(getRandomNeighbor());
        }
    }
}
