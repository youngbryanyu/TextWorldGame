import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player player = new Player("Yo Bunty", "ladies man");
        Level level = new Level(player);
        level.addRoom("hall", "a really long, big black... hallway");
        level.addRoom("closet", "there is nothing in the closet");
        level.addRoom("dungeon", "it is very dark and cold here...");
        level.addRoom("your mom's bedroom", "it's sexy time");

        level.addUndirectedEdge("hall", "dungeon");
        level.addUndirectedEdge("hall", "closet");
        level.addUndirectedEdge("dungeon", "your mom's bedroom");

        level.getRoom("hall").addItem("samosa", "delicious");
        level.getRoom("closet").addItem("gun", "i have big guns");

        player.setCurrentRoom(level.getRoom("hall"));
        level.createRandomCreatures(10);

        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.print("What do you want to do? > ");
            response = s.nextLine();

            String[] words = response.split(" ");

            boolean playerMoved = false;

            if (words.length >= 2 && words[0].equals("go") && words[1].equals("to") && response.length() >= 6) {
                String name = response.substring(6);
                if (player.getCurrentRoom().getNeighbor(name) != null) {
                    player.moveToRoom(name);
                    playerMoved = true;
                }
                if (playerMoved) {
                    level.updateAllCreatures();
                }
            } else if (response.equals("look")) {
                System.out.println("You can go to: \n" + player.getCurrentRoom().getNeighborNames() + "\n");
                System.out.print("Items in the room: \n" + player.getCurrentRoom().displayItems());
                if (player.getCurrentRoom().getItems().size() == 0) {
                    System.out.println("(none)");
                } else System.out.println();
            } else if (words.length >= 2 && words[0].equals("add") && words[1].equals("room") && response.length() >= 9) {
                String name = response.substring(9);
                level.addRoom(name, "");
                level.addUndirectedEdge(player.getCurrentRoom().getName(), name);
            } else if (words.length >= 1 && words[0].equals("take") && response.length() >= 5) {
                String name = response.substring(5);
                Item item = player.getCurrentRoom().removeItem(name);
                if (item != null) player.addItem(item);
            } else if (words.length >= 1 && words[0].equals("drop") && response.length() >= 5) {
                String name = response.substring(5);
                Item item = player.removeItem(name);
                if (item != null) player.getCurrentRoom().addItem(item);
            } else if (words.length >= 1 && words[0].equals("bag")) {
                player.displayInventory();
            } else if (response.equals("quit")) {
                // do nothing
            } else {
                System.out.println("Commands: \n-> go to <roomname> \n-> look \n-> add room <roomname> \n-> quit ");
            }
            System.out.println();
        } while (!response.equals("quit"));
    }
}
