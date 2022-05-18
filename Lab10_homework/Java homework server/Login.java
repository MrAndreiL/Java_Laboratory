import java.io.PrintWriter;

/***
 * Login class. On client command, log in client into server's data structure.
 * Send the client back an adequate respone based on the successfullness of the
 * insertion.
 */
public class Login {
    /***
     * @param name - registered name
     * @param ip   - incoming IPv4
     * @param port - incoming port
     */
    public void resolve(String name, String ip, String port, PrintWriter out) {
        // 1. Insert into serve'r logged in data structure.
        boolean status = Server.insertActiveUsers(name, ip, port);

        // 2. Provide specific message on success or fail.
        if (status) {
            out.println("You have logged in successfully!");
            System.out.println(name + " has logged in!");
        } else {
            out.println("Invalid name or a user with the same name is already logged in!");
        }
        out.flush();
    }
}
