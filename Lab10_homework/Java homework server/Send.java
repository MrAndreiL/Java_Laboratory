import java.io.PrintWriter;

/**
 * Send class. Provide friends with the message given by user.
 */
public class Send {
    public void resolve(String name, String message, PrintWriter out) {
        Server.addMessagesToFriends(name, message);

        out.println("Message sent to friends!");
        out.flush();
    }
}
