public class dropCommand implements Command {
    private Player player;
    private String itemName;

    public dropCommand(Player player) {
        this.player = player;
    }

    public void init(String input) {
        this.itemName = parseItemName(input);
    }

    private String parseItemName(String input) {
        return input.substring(input.indexOf(" ") + 1);
    }

    public boolean execute() {
        boolean success = player.dropItem(itemName);
        if (success)
            System.out.println("You dropped the " + itemName);
        return success;
    }
}
