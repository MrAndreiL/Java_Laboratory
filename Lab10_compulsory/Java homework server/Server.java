import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/***
 * The Server class will create a TCP server socket.
 * Will run at port 3005.
 * Waits until clients make requests and defer to a thread.
 */
public class Server {
    private static final int PORT = 3005;

    private static ServerSocket server = null;

    private static final int backlog = 10;

    // Server will keep running as long as working loop is active.
    private static boolean workingLoop = true;

    // When should stop is triggered, turn off working loop after making sure all
    // other thread have stopped.
    private static boolean shouldStop = false;

    // Thread counter. When reaches 0 and shouldStop is true
    // close working loop.
    private static int threadsActive = 0;

    /***
     * Server constructor will instantiate a new Server Socket and wait
     * for client requests. When a new request is made, redirect to a child thread.
     */
    public static void startServer() {
        // 1. Create the system's server socket.
        try {
            server = new ServerSocket(PORT, backlog);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // 2. Create working loop.
        while (workingLoop) {
            System.out.println("Waiting for a client...");

            // 3. Accept (blocking operation) incoming client requests.
            // Can only accept new requests if shouldStop is false.
            Socket socket = null;
            try {
                socket = server.accept();
            } catch (IOException ex) {
                System.err.println("Eroare la socket sau server-ul s-a inchis.");
            }

            // 4. Create response on a separete thread to treat request.
            if (socket != null) {
                new Response(socket).start();
                threadsActive++;
            }
        }

        // 5. Close socket.
        try {
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /***
     * Will deactivate the server by triggering should stop. The working
     * loop will end when no more threads are active.
     */
    public static void turnOffServer() {
        shouldStop = true;
    }

    /***
     * In order to gracefully stop the server, all threads need to
     * have their tasks finished first. When a thread fulfils it's task,
     * decrement the counter.
     */
    public static synchronized void deactivateThread() {
        threadsActive--;
    }

    // Returns the number of threads that are active.
    public static synchronized int getActiveThreads() {
        return threadsActive;
    }

    // Returns whether or not the server should stop.
    public static synchronized boolean getServerStatus() {
        return shouldStop;
    }

    // Get the server socket so that it can be closed from another thread.
    public static synchronized ServerSocket getServerSocket() {
        return server;
    }

    public static synchronized void closeWorkingLoop() {
        workingLoop = false;
    }
}
