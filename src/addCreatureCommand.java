public class addCreatureCommand implements Command {

    Player player;
    private Creature creature;

    public addCreatureCommand(Player player) {
        this.player = player;
    }

    public void init(String input) {
        if (getSecondWordIn(input).equals("chicken"))
            creature = new Chicken(player.getCurrentRoom());
        else if (getSecondWordIn(input).equals("wumpus"))
            creature = new Wumpus(player.getCurrentRoom(), player);
        else if (getSecondWordIn(input).equals("popstar"))
            creature = new Popstar(player.getCurrentRoom(), player);
    }

    public boolean execute() {
        if (creature == null) return false;
        player.getCurrentRoom().addCreature(creature);
        return true;
    }

    private static String getSecondWordIn(String input) {
        String[] words = input.split(" ");
        if (words.length > 0) return words[1];
        return "";
    }
}
