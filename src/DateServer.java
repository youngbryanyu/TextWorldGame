import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServer {
    public static final int PORT = 9090;

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

        System.out.println("[SERVER] Waiting for client connection...");
        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client!");

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        String date = (new Date()).toString();
        System.out.println("[SERVER] Sender date " + date);
        out.println(date);

        System.out.println("[SERVER] Sent date. Closing.");

        listener.close();
        client.close();
    }

}
