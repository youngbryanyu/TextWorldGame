import java.util.ArrayList;

public class Player {
    private String name, description;
    private ArrayList<Item> items;
    private Level.Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String name) {
        for (Item e : items) {
            if (e.getName().equals(name)) {
                items.remove(e);
                return e;
            }
        }
        return null;
    }

    public boolean destroyItem(String name) {
        for (Item e : items) {
            if (e.getName().equals(name)) {
                return items.remove(e);
            }
        }
        return false;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void displayInventory() {
        System.out.println("Items: ");
        String inventory = "";
        for (Item e : items) {
            inventory += e.getName() + " >> " + e.getDescription() + "\n";
        }
        if (inventory.length() > 1) inventory = inventory.substring(0, inventory.length() - 1);
        System.out.println(inventory);
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        currentRoom = newRoom;
    }

}
