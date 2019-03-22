import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Node root = new Node("hall");

        root.addNeighbor(new Node("closet"));
        root.addNeighbor(new Node("bedroom"));

        root.getNeighbor("closet").addNeighbor(root);
        root.getNeighbor("bedroom").addNeighbor(new Node("secret chamber"));
        root.getNeighbor("bedroom").getNeighbor("secret chamber").addNeighbor(new Node("Canada"));
        root.getNeighbor("bedroom").getNeighbor("secret chamber").addNeighbor(new Node("tilted towers"));
        root.getNeighbor("bedroom").getNeighbor("secret chamber").getNeighbor("tilted towers").addNeighbor(root);

        Node currentRoom = root;
        String response = "";
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("You are currently in the " + currentRoom.getName());
            System.out.println("You can go to the: " + currentRoom.getNeighborNames());

            System.out.println("Type the name of the room you want to go to: ");
            response = in.nextLine();

            Node nextRoom = currentRoom.getNeighbor(response);
            if (nextRoom == null && !response.equalsIgnoreCase("quit")) {
                System.out.println("You can't go to " + response + ", try again");
            } else {
                currentRoom = nextRoom;
            }

        } while (!response.equalsIgnoreCase("quit"));
    }
}
