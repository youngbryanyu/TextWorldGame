public abstract class Creature {
    protected Level.Room currentRoom;

    public abstract void act();

    protected void moveToRoom(Level.Room nextRoom) {
        this.currentRoom.decrementCreatureCount(this);
        this.currentRoom = nextRoom;
        this.currentRoom.incrementCreatureCount(this);
    }

    protected Level.Room getRandomNeighbor() {
        return currentRoom.getRandomNeighbor();
    }
}
