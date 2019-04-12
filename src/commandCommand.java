public class commandCommand implements Command {
    public void init(String input) {

    }

    public boolean execute() {
        System.out.println("Commands: \n-> go-to <roomnName> \n-> look \n-> add room <roomName> \n-> quit \n-> take <item> \n-> drop <item> \n-> destroy <item> \n-> commands \n-> connect <room1> and <room2> \n-> add-room <roomName>");
        return true;
    }
}
