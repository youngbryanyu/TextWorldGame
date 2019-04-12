import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, Command> commands;
    private static Level level;
    private static Player player;

    public static void main(String[] args) {
        player = new Player("yo", "bunty");
        level = new Level(player);
        initiateLevel();

        commands = new HashMap<>();
        initCommands();

        lookUpCommand("commands").execute();
        System.out.println("\nYou magically appeared in the " + player.getCurrentRoom().getName());

        String response = "";
        Scanner input = new Scanner(System.in);

        do {
            System.out.print("> ");
            response = input.nextLine();

            Command command = lookUpCommand(response); // init
            command.execute();

        } while (!response.equals("quit"));
    }

    public static void initCommands() {
        commands.put("take", new TakeCommand(player));
        commands.put("look", new LookCommand(player));
        commands.put("add-room", new AddRoomCommand(level));
        commands.put("connect", new ConnectRoomsCommand(level));
        commands.put("go-to", new goToCommand(player, level)); // creatures move when player moves
        commands.put("drop", new dropCommand(player));
        commands.put("destroy", new destroyCommand(player));
        commands.put("add-creature", new addCreatureCommand(player));
        commands.put("commands", new commandCommand());
    }

    private static Command lookUpCommand(String response) {
        String commandWord = getFirstWordIn(response);
        Command c = commands.get(commandWord);

        if (c == null) return new EmptyCommand();
        c.init(response);

        return c;
    }

    private static String getFirstWordIn(String response) {
        String[] words = response.split(" ");
        if (words.length > 0) return words[0];
        return "";
    }

    private static void initiateLevel() {
        level.addRoom("hall", "a really long, big black... hallway");
        level.addRoom("closet", "there is nothing in the closet");
        level.addRoom("dungeon", "it is very dark and cold here...");
        level.addRoom("bedroom", "it's sexy time");

        level.addUndirectedEdge("hall", "dungeon");
        level.addUndirectedEdge("hall", "closet");
        level.addUndirectedEdge("dungeon", "bedroom");

        level.getRoom("hall").addItem("samosa", "delicious");
        level.getRoom("closet").addItem("gun", "i have big guns");
        player.setCurrentRoom(level.getRoom("bedroom"));

        level.createRandomCreatures(5);
    }
}
