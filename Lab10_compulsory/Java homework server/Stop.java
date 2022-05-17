import java.io.PrintWriter;

/***
 * Simple class that stops server activity by turning on shouldStop parameter.
 * The server will stop only when all threads have finished their tasks.
 */
public class Stop {
    public void resolve(PrintWriter out) {
        // 1. Alert server to shut down gracefully.
        Server.turnOffServer();

        // 2. Send message to client.
        out.println("Server is stopping");
        out.flush();
    }
}
