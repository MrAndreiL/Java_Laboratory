import java.util.Scanner;

import java.net.Socket;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/***
 * The client class will read input from user, connect to the server
 * and send request based on available commands. Will turn off when user
 * inputs "exit".
 */
public class Client {
    // Basic server configuration details.
    private static final String address = "127.0.0.1"; // loopback address
    private static final int PORT = 3005;

    // Working loop. Continuosly read input from user until exit is inputed.
    private boolean workingLoop = true;

    /**
     * startClient will take user input, parse it and send it to server.
     */
    public void startClient() {
        Scanner scanner = new Scanner(System.in);

        Socket socket = null;
        try {
            socket = new Socket(address, PORT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while (workingLoop) {
            String input = scanner.nextLine();

            try {
                // 1. Create in and out interfaces.
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 2. Test if exit is given.
                if (input.equals("exit")) {
                    exitWorkingLoop(out);
                    scanner.close();
                    out.println(input);
                    continue;
                }

                // 3. Send line written as input.
                out.println(input);

                // 4. Wait for response from the server and output it.
                String response = in.readLine();

                // 5. Test if server has decided to close and exit client.
                if (response.equals("Server is stopping")) {
                    workingLoop = false;
                }

                System.out.println(response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // 6. Close connection to server.
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // On exit, close working loop, but also tell the server about it.
    public void exitWorkingLoop(PrintWriter out) {
        // 1. If exit is given, close working loop.
        System.out.println("Client is closing.");
        workingLoop = false;

        // 2. Tell client to disconnect if previously connected.
        out.println("exit");
    }
}
