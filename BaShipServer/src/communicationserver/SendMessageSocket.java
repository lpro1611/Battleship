package communicationserver;

import java.io.*;

/**
 * Send messages to the client
 * 
 * @author Diog Dinis
 */
public class SendMessageSocket extends Thread {
    private final PrintWriter out;
    private final String message;
    
    /**
     * Class Constructor specifying the socket and the properties
     * 
     * @param message   message to send to the client
     * @param out stream that connets to the Socket 
     */
    public SendMessageSocket(String message, PrintWriter out) {
        super("SendMessageSocket");
        this.out = out;
        this.message = message;
    }
    
    /**
     * Communicates with the client.
     * <p>
     * This method is run every time a server nedd to sends asynchronous 
     * messages to the client.
     */
    @Override
    public void run() {
            if (message != null) {
                out.println(message);
            }
            
            // o socket não é fechado aqui mas em ReceiveMessageSocket
    }
}
