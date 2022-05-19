import java.lang.Thread;

import java.net.Socket;
import java.net.InetSocketAddress;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

/***
 * Response object. Takes socket instance and addresses client's
 * request. Runs on a separate thread.
 */
public class Response extends Thread {
    // Socket instance provided by server to achieve concurrency.
    private Socket socket = null;

    // Incoming Ip and port.
    private String incomingIp = null;
    private String incomingPort = null;

    // Working loop flag.
    private boolean workingLoop = true;

    // Constructor receives socket instance.
    public Response(Socket socket) {
        this.socket = socket;

        // Convert to TCP and get incoming ip and port.
        InetSocketAddress sockaddr = (InetSocketAddress) socket.getRemoteSocketAddress();
        this.incomingPort = String.valueOf(sockaddr.getPort());

        this.incomingIp = socket.getRemoteSocketAddress().toString();
    }

    @Override
    public void run() {
        while (workingLoop) {
            try {
                // 1. Open stream input entry and read request.
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 2. Open stream output entry to prepare response for client.
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                // 3. Parse request and flow based on command.
                String request = in.readLine();
                String[] content = request.split(" ");

                /** 4. Based on content[0] command, create an object corresponding to it. **/
                if (content[0].equals("stop")) {
                    Stop stop = new Stop();
                    stop.resolve(out);

                    // Call stopper thread that closes server connection.
                    new Stopper(Server.getServerSocket()).start();
                }

                if (content[0].equals("register")) {
                    Register register = new Register();
                    register.resolve(content[1], out);
                }

                // When client calls for exit, delete it from the logged in users.
                if (content[0].equals("exit")) {
                    String name = Server.isActiveUser(incomingIp, incomingPort);
                    if (name != null) {
                        Server.disconnectUser(name);
                        System.out.println(name + " has logged out!");
                    }

                    Server.deactivateThread();
                    workingLoop = false;
                }

                if (content[0].equals("login")) {
                    Login login = new Login();
                    login.resolve(content[1], incomingIp, incomingPort, out);
                }

                if (content[0].equals("friend")) {
                    // Create a friends list based on the content received.
                    List<String> friends = new ArrayList<>();
                    for (int i = 1; i < content.length; i++) {
                        friends.add(content[i]);
                    }

                    // Create friend object and let it resolve connection to friends.
                    String name = Server.isActiveUser(incomingIp, incomingPort);
                    if (name != null) {
                        Friend friend = new Friend();
                        friend.resolve(name, friends, out);
                    }
                }

                if (content[0].equals("send")) {
                    // Concatenate message.
                    String message = "";
                    for (int i = 1; i < content.length; i++) {
                        message += content[i];
                        message += " ";
                    }

                    // Create send object to distribute to friends.
                    String name = Server.isActiveUser(incomingIp, incomingPort);
                    if (name != null) {
                        Send send = new Send();
                        send.resolve(name, message, out);
                    }
                }

                if (content[0].equals("read")) {
                    Read read = new Read();

                    String name = Server.isActiveUser(incomingIp, incomingPort);
                    if (name != null) {
                        read.resolve(name, out);
                    } else {
                        out.println("Sorry, you are not logged in");
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
