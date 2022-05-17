import java.lang.Thread;

import java.net.ServerSocket;

import java.io.IOException;

/***
 * Stopper class gracefully tries to stop the server by calling close.
 */
public class Stopper extends Thread {
    private ServerSocket server = null;

    public Stopper(ServerSocket server) {
        this.server = server;
    }

    /***
     * Test if closing condition have been met.
     * should stop should be true and no more threads are in execution.
     */
    @Override
    public void run() {
        if (Server.getServerStatus() && Server.getActiveThreads() == 0) {
            try {
                server.close();
                Server.closeWorkingLoop();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
