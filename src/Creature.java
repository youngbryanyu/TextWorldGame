public abstract class Creature {
    protected Level.Room currentRoom;

    public abstract void act();

    protected void moveToRoom(Level.Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    protected Level.Room getRandomNeighbor() {
        return currentRoom.getRandomNeighbor();
    }
}
