public class AddRoomCommand implements Command {
    Level level;
    String roomName;

    public AddRoomCommand(Level level) {
        this.level = level;
    }

    public void init(String input) {
        this.roomName = parseRoomName(input);
    }

    private String parseRoomName(String userString) {
        return userString.substring(userString.indexOf(" ") + 1);
    }

    public boolean execute() {
        level.addRoom(roomName);
        return true;
    }
}
