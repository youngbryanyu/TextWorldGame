import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Level level1 = new Level();
        level1.addRoom("hall", "a really long, big black... hallway");
        level1.addRoom("closet", "there is nothing in the closet");
        level1.addRoom("dungeon", "it is very dark and cold here...");
        level1.addRoom("your mom's bedroom", "it's sexy time");

        level1.addUndirectedEdge("hall", "dungeon");
        level1.addUndirectedEdge("hall", "closet");
        level1.addUndirectedEdge("dungeon", "your mom's bedroom");

        level1.getRoom("hall").addItem("samosa", "delicious");
        level1.getRoom("closet").addItem("gun", "i have big guns");
        level1.getRoom("dungeon").addItem("mini-bunty", "he looks chindian");
        level1.getRoom("your mom's bedroom").addItem("trojan", "protection");

        Player player = new Player("Yo Bunty", "ladies man");
        player.setCurrentRoom(level1.getRoom("hall"));

        ArrayList<Chicken> chickens = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Chicken c = new Chicken(level1.getRandomRoom());
            chickens.add(c);
        }

        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.print("What do you want to do? > ");
            response = s.nextLine();

            String[] words = response.split(" ");

            if (words.length >= 2 && words[0].equals("go") && words[1].equals("to") && response.length() >= 6) {
                String name = response.substring(6);
                if (player.getCurrentRoom().getNeighbor(name) != null) {
                    player.moveToRoom(name);
                }
                int chickenCount = 0;
                for (int i = 0; i < chickens.size(); i++) {
                    Chicken c = chickens.get(i);
                    c.randomizeRoom();
                    if(c.getCurrentRoom().getName().equals(player.getCurrentRoom().getName())) chickenCount++;
                }
                if(chickenCount != 0) System.out.println("There are " + chickenCount + " chickens in the " + player.getCurrentRoom().getName());
            } else if (response.equals("look")) {
                System.out.println("You can go to: \n" + player.getCurrentRoom().getNeighborNames() + "\n");
                System.out.print("Items in the room: \n" + player.getCurrentRoom().displayItems());
                if (player.getCurrentRoom().getItems().size() == 0) {
                    System.out.println("(none)");
                } else System.out.println();
            } else if (words.length >= 2 && words[0].equals("add") && words[1].equals("room") && response.length() >= 9) {
                String name = response.substring(9);
                level1.addRoom(name, "");
                level1.addUndirectedEdge(player.getCurrentRoom().getName(), name);
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
