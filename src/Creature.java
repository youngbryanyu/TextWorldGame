public abstract class Creature {
    protected Level.Room currentRoom;

    public abstract void move();

    protected abstract void moveRandom();

    protected Level.Room getCurrentRoom() {
        return currentRoom;
    }

    protected void setRoom(Level.Room nextRoom) {
        this.currentRoom = nextRoom;
    }
}
