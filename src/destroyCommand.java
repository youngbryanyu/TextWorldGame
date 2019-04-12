public class destroyCommand implements Command {
    private Player player;
    private String itemName;

    public destroyCommand(Player player) {
        this.player = player;
    }

    public void init(String input) {
        this.itemName = parseItemName(input);
    }

    private String parseItemName(String input) {
        return input.substring(input.indexOf(" ") + 1);
    }

    public boolean execute() {
        boolean success = player.destroyItem(itemName);
        if (success)
            System.out.println("You destroyed the " + itemName);
        return success;
    }
}
