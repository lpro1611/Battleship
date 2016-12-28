package communicationserver;

import java.net.*;
import java.io.*;

/**
 * Receive messages from the client
 * 
 * @author Afonso Ferreira
 */
public class ReceiveMessageSocket extends Thread {
    private final Socket socket;
    private final boolean listening  = true;
    
    /**
     * Class Constructor specifying the socket and the properties
     * 
     * @param socket socket to communicate with the client
     */
    public ReceiveMessageSocket(Socket socket) {
        super("ReceiveMessageSocket");
        this.socket = socket;
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
            BufferedReader in;
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputLine, reply = null;
                do {
                    if((inputLine = in.readLine()) != null) {
                        reply = Protocol.protocolDecode(inputLine, out);
                        
                        if (reply != null) {
                            out.println(reply);
                            
                            if (reply.equals("exit")) {
                                break;
                            }
                        }
                    }
                    
                } while (listening);
            }
            
            in.close();
            socket.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
