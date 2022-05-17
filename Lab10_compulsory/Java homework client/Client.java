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
        while (workingLoop) {
            String input = scanner.nextLine();

            // 1. If exit is given, close working loop.
            if (input.equals("exit")) {
                // Close scanner and client.
                System.out.println("Client is closing.");
                workingLoop = false;

                scanner.close();
                continue;
            }

            // 2. Create client socket and connect to server.
            Socket socket = null;
            try {
                socket = new Socket(address, PORT);

                // 3. Create in and out interfaces.
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 4. Send line written as input.
                out.println(input);

                // 5. Wait for response from the server and output it.
                String response = in.readLine();
                System.out.println(response);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally { // 6. close socket connection.
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
