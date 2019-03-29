import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Level g = new Level();
        g.addRoom("hall", "a really long, big black... hallway");
        g.addRoom("closet", "there is nothing in the closet");
        g.addRoom("dungeon", "it is very dark and cold here...");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        g.getRoom("hall").addItem("samosa", "delicious");
        g.getRoom("closet").addItem("gun", "i have big guns");
        g.getRoom("dungeon").addItem("mini-bunty", "he looks chindian");

        Player player = new Player("Yo Bunty", "ladies man");
        player.setCurrentRoom(g.getRoom("hall"));

        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.print("What do you want to do? > ");
            response = s.nextLine();

            String[] words = response.split(" ");

            if (words.length >= 2 && words[0].equals("go") && words[1].equals("to")) {
                String name = response.substring(6);
                if (player.getCurrentRoom().getNeighbor(name) != null) {
                    player.moveToRoom(name);
                }
            } else if (response.equals("look")) {
                System.out.println("You can go to: \n" + player.getCurrentRoom().getNeighborNames() + "\n");
                System.out.print("Items in the room: \n" + player.getCurrentRoom().displayItems());
                if (player.getCurrentRoom().getItems().size() == 0) {
                    System.out.println("(none)");
                } else System.out.println();
            } else if (words.length >= 2 && words[0].equals("add") && words[1].equals("room")) {
                String name = response.substring(9);
                g.addRoom(name, "");
                g.addUndirectedEdge(player.getCurrentRoom().getName(), name);
            } else if (words.length >= 1 && words[0].equals("take")) {
                String name = response.substring(5);
                Item item = player.getCurrentRoom().removeItem(name);
                if (item != null) player.addItem(item);
            } else if (words.length >= 1 && words[0].equals("drop")) {
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
