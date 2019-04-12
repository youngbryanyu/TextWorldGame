public class goToCommand implements Command {

    private Player player;
    private Level level;
    private String roomName;

    public goToCommand(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    public void init(String input) {
        roomName = parseRoomName(input);
    }

    private String parseRoomName(String input) {
        return input.substring(input.indexOf(" ") + 1);
    }


    public boolean execute() {
        if (level.getRoom(roomName) == null) return false;
        player.moveToRoom(roomName);
        System.out.println("You are now in the " + roomName);

        level.updateAllCreatures();
        player.getCurrentRoom().checkCreatures();
        player.getCurrentRoom().checkNearbyCreatures();
        return true;
    }
}
