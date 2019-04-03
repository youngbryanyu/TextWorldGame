public abstract class Creature {
    Level.Room currentRoom;

    public abstract void move(Level.Room nextRoom);

    public abstract void randomizeRoom();
}
