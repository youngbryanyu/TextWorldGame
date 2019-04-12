public class Popstar extends Creature {

    Player player;

    public Popstar(Level.Room currentRoom, Player player) {
        this.currentRoom = currentRoom;
        this.player = player;
    }

    public void act() {
        if (currentRoom.getPlayerContainingNeighbors().size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getPlayerContainingNeighbors()));
        } else if (currentRoom.getCommonNeighborsWithPlayer().size() > 0) {
            moveToRoom(currentRoom.getRandomNeighbor(currentRoom.getCommonNeighborsWithPlayer()));
        } else if (currentRoom.equals(player.getCurrentRoom())) {

        } else {
            moveToRoom(getRandomNeighbor());
        }
    }
}