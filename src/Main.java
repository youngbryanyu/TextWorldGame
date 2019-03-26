import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("hall", "a really long, big black... hallway");
        g.addNode("closet", "there is nothing in the closet");
        g.addNode("dungeon", "it is very dark and cold here...");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        Graph.Node current = g.getNode("hall");

        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + current.getName());
            System.out.print("What do you want to do? > ");
            response = s.nextLine();

            if (response.length() >= 6 && response.substring(0, 6).equals("go to ")) {
                String name = response.substring(6);
                if (current.getNeighbor(name) != null) current = current.getNeighbor(name);
            } else if (response.equals("look")) {
                System.out.println("You can go to: \n" + current.getNeighborNames());
            } else if (response.length() >= 9 && response.substring(0, 9).equals("add room ")) {
                String name = response.substring(9);
                g.addNode(name, "");
                g.addDirectedEdge(current.getName(), name);
            } else if (response.equals("quit")) {
                // do nothing
            } else {
                System.out.println("Commands: \n-> go to <roomname> \n-> look \n-> add room <roomname> \n-> quit ");
            }
            System.out.println();
        } while (!response.equals("quit"));

    }
}
