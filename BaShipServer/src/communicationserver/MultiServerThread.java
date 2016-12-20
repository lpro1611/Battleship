package communicationserver;

import java.net.*;
import java.io.*;
import java.util.Properties;

/**
 * Communicates with the client
 * @author Alunos-i221-16
 */
public class MultiServerThread extends Thread {
    private Socket socket = null;
    private final Properties props;
 /**
  * Class Constructor specifying the socket and the properties
  * 
  * @param socket   socket to communicate with the client
  * @param props    DB's connection properties
  */
    public MultiServerThread(Socket socket, Properties props) {
        super("MultiServerThread");
        this.socket = socket;
        this.props = props;
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
    
            Protocol p = new Protocol();
            
            if((inputLine = in.readLine()) != null) {
                reply = p.ProtocolDecode(inputLine, props);
            }
            
            out.print(reply);
            
            out.close();
            in.close();
            socket.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
