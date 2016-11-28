/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package communicationserver;

import java.net.*;
import java.io.*;

/**
 *Class that comunciates with the client on the server side
 * @author Alunos-i221-16
 */
import java.util.Properties;
public class MultiServerThread extends Thread {
    private Socket socket = null;
    private final Properties props;
 /**
  * Constructor for the Class
  * <p>
  * The constructor generates the object, and
  * saves the socket and the properties of the  comunication
  * <p>
  * @param socket Socket to communicate with the client
  * @param props  Properties of the communication
  */
    public MultiServerThread(Socket socket, Properties props) {
        super("MultiServerThread");
        this.socket = socket;
        this.props = props;
    }
    /**
     * The method that communicates with the client.
     * <p>
     * This method is run everytime a client is accepted and it, receives and sends messages
     * to the client. Received messages are given to the Protocol of the communication. Replies are 
     * sent to the client, and afterward the socket is closed.
     */
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
