public class ConnectRoomsCommand implements Command {

    private Level level;
    private String room1Name, room2Name;

    public ConnectRoomsCommand(Level level) {
        this.level = level;
    }

    public void init(String input) {
        room1Name = getFirstRoom(input);
        room2Name = getSecondRoom(input);
    }

    public String getFirstRoom(String input) {
        return input.substring(input.indexOf(" ") + 1, input.indexOf(" and "));
    }

    public String getSecondRoom(String input) {
        return input.substring(input.indexOf(" and ") + 5).trim();
    }

    public boolean execute() {
        if (level.getRoom(room1Name) == null || level.getRoom(room2Name) == null) return false;
        level.addUndirectedEdge(room1Name, room2Name);
        return true;
    }
}
