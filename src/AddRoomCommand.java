public class AddRoomCommand implements Command {
    private Level level;
    private String roomName;

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
        System.out.println("You created the " + roomName);
        return true;
    }
}
