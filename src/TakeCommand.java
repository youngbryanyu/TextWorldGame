public class TakeCommand implements Command {
    Level level;
    String itemName;

    public TakeCommand(Level level) {
        this.level = level;
    }

    public void init(String userString) {
        this.itemName = parseItemName(userString);
    }

    private String parseItemName(String userString) {
        return userString.substring(userString.indexOf(" ") + 1);
    }

    public boolean execute() {
        Player p = level.getPlayer();
        boolean success = p.takeItem(itemName);
        return success;
    }
}
