import java.util.List;

import java.io.PrintWriter;

/***
 * Friend class. Takes name and friends list.
 * Update the friendship list inside the server.
 * Send back message after inserting.
 */
public class Friend {
    public void resolve(String name, List<String> friends, PrintWriter out) {
        // 1. Update friends list inside server.
        Server.addFriends(name, friends);

        // 2. Return message to client.
        out.println("Friends list updated");
        out.flush();
    }
}
