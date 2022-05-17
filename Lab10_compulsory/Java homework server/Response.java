import java.lang.Thread;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

/***
 * Response object. Takes socket instance and addresses client's
 * request. Runs on a separate thread.
 */
public class Response extends Thread {
    // Socket instance provided by server to achieve concurrency.
    Socket socket = null;

    // Constructor receives socket instance.
    public Response(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 1. Open stream input entry and read request.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 2. Open stream output entry to prepare response for client.
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            // 3. Parse request and flow based on command.
            String request = in.readLine();
            String[] content = request.split(" ");

            // 4. Based on content[0] command, create an object corresponding to it.
            if (content[0].equals("stop")) {
                Stop stop = new Stop();
                stop.resolve(out);

                // Tell the server that this thread has finished it's task.
                Server.deactivateThread();

                // Call stopper thread that closes server connection.
                new Stopper(Server.getServerSocket()).start();
                return;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
