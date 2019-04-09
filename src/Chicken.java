public class Chicken extends Creature {

    public Chicken(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void act() {
        moveToRoom(getRandomNeighbor());
    }
}
