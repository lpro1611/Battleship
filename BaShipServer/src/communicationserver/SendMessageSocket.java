package communicationserver;

import java.net.*;
import java.io.*;

/**
 * Send messages to the client
 * 
 * @author Diog Dinis
 */
public class SendMessageSocket extends Thread {
    private final Socket socket;
    private final String message;
    
    /**
     * Class Constructor specifying the socket and the properties
     * 
     * @param message   message to send to the client
     * @param socket    socket to communicate with the client
     */
    public SendMessageSocket(String message, Socket socket) {
        super("SendMessageSocket");
        this.socket = socket;
        this.message = message;
    }
    
    /**
     * Communicates with the client.
     * <p>
     * This method is run every time a client is accepted and it 
     * receives and sends messages to the client. Received messages 
     * are sent to the communication protocol. Replies are sent to 
     * the client, after which the socket is closed.
     */
    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            if (message != null) {
                out.println(message);
            }
            
            out.close();
            // o socket não é fechado aqui mas em ReceiveMessageSocket
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
