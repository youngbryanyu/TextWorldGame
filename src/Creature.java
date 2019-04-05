public abstract class Creature {
    private Level.Room currentRoom;

    public void move(Level.Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    protected abstract void moveRandom();

    protected Level.Room getCurrentRoom(){
        return currentRoom;
    }

    protected void moveToRoom(Level.Room nextRoom) {
        currentRoom.removeCreature(this);
        move(nextRoom);
    }

    protected void setRoom(Level.Room nextRoom){
        this.currentRoom = nextRoom;
    }
}
