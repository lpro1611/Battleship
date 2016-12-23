package communicationserver;

import java.net.*;
import java.io.*;

/**
 * Communicates with the client
 * @author Alunos-i221-16
 */
public class ReceiveMessageSocket extends Thread {
    private Socket socket = null;
 /**
  * Class Constructor specifying the socket and the properties
  * 
  * @param socket   socket to communicate with the client
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
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
            String inputLine, reply = null;
            
            while (!reply.equals("exit")) {
                if((inputLine = in.readLine()) != null) {
                    reply = Protocol.protocolDecode(inputLine);
                }

                if (reply != null) {
                    out.print(reply);
                }
            }
            
            out.close();
            in.close();
            socket.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
