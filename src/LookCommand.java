public class LookCommand implements Command {
    Player player;

    public LookCommand(Player player) {
        this.player = player;
    }

    public void init(String userString) {

    }

    public boolean execute() {
        System.out.println("You can go to: \n" + player.getCurrentRoom().getNeighborNames() + "\n");
        System.out.print("Items in the room: \n" + player.getCurrentRoom().displayItems());
        if (player.getCurrentRoom().getItems().size() == 0)
            System.out.println("(none)");
        System.out.println();
        return true;
    }
}