import java.io.PrintWriter;

/***
 * Register class. Creates an object that inserts into server's network.
 * Return a specific message if insertion was successful or not.
 */
public class Register {
    /***
     * Resolve thread request. Provide a message back to client.
     * 
     * @param name - name to be inserted
     * @param out  - open stream to client socket.
     */
    public void resolve(String name, PrintWriter out) {
        // 1. Insert into server's network.
        boolean status = Server.insertRegistrationName(name);

        // 2. Send client "<<name>> successfully registered!"
        // or "<<name>> already exists".
        if (status) {
            out.println(name + " successfully registered!");
            System.out.println("New user registered!");
        } else {
            out.println(name + " already exists!");
        }

        out.flush();
    }
}
