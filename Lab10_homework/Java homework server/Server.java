import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

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

    // List of registered names available.
    private static List<String> network = new ArrayList<>();

    // DataStructure for managing logged in users based on their registered name, IP
    // and port.
    private static Map<String, Map<String, String>> logged = new HashMap<>();
    // ^^^^^^^^^^^^^^^ {name -> {IP -> PORT }} ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    // Map -> array relationship for storing friend list for each username.
    private static Map<String, List<String>> friendships = new HashMap<>();

    // Map -> list of string arrays for sending messages.
    private static Map<String, List<String>> messages = new HashMap<>();

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

    /***
     * Insert a new name into the registration list.
     * Make sure the name does not already exist.
     * 
     * @param name to be inserted
     * @return true if a new name has been inserted successfully, false otherwise.
     */
    public static synchronized boolean insertRegistrationName(String name) {
        if (!network.contains(name)) {
            network.add(name);
            return true;
        }
        return false;
    }

    /***
     * Log in a new user using the map data structure. Check if it's a valid name
     * first.
     * 
     * @param name - registered name to be logged in.
     * @param ip   - incoming ip
     * @param port - incoming port
     * @return - true if successful log in occured, false otherwise
     */
    public static synchronized boolean insertActiveUsers(String name, String ip, String port) {
        // 1. Check if the name is registered.
        if (!network.contains(name)) {
            return false;
        }

        // 2. Check if another client is already logged in with that name.
        if (logged.keySet().contains(name)) {
            return false;
        }

        // 3. Insert into the data structure.
        Map<String, String> pair = new HashMap<>();
        pair.put(ip, port);
        logged.put(name, pair);

        return true;
    }

    /***
     * Based on the given name, remove the user from the logged in data structure.
     * 
     * @param name - name to be disconnected on client's exit.
     */
    public static synchronized void disconnectUser(String name) {
        logged.remove(name);
    }

    /***
     * Given the incoming Ip and port, check if the user is authenticated or not.
     * 
     * @param Ip   - incoming IPv4 Ip
     * @param port - incoming port
     * @return name associated with the given logged in user, null if no user exists
     *         with such name.
     */
    public static synchronized String isActiveUser(String Ip, String port) {
        Map<String, String> pair = new HashMap<>();
        pair.put(Ip, port);

        for (Map.Entry<String, Map<String, String>> entry : logged.entrySet()) {
            if (entry.getValue().equals(pair)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /***
     * Insert new friends into the hashmap to the appropriate name.
     * Add only the friends that have registered names.
     * 
     * @param name    - user name
     * @param friends - friendlist
     */
    public static synchronized void addFriends(String name, List<String> friends) {
        if (!friendships.containsKey(name)) { // if the name has no friends associated with it
            friendships.put(name, new ArrayList<>());
        }

        for (String friend : friends) {
            if (network.contains(friend)) { // if the friend has a valid username
                // add only new friends
                if (!friendships.get(name).contains(friend)) {
                    friendships.get(name).add(friend);
                }
            }
        }
    }

    /***
     * 
     * @param name    - user name whose friends will receive the message.
     * @param message
     */
    public static synchronized void addMessagesToFriends(String name, String message) {
        for (String friend : friendships.get(name)) {
            if (!messages.containsKey(friend)) {
                messages.put(friend, new ArrayList<>());
            }
            messages.get(friend).add(message);
        }
    }
}
