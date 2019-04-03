public abstract class Creature {
    private Level.Room currentRoom;

    public abstract void move(Level.Room nextRoom);

    public abstract void randomizeRoom();

    public abstract Level.Room getCurrentRoom();
}
