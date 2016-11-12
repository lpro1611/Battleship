/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package communicationserver;

import java.net.*;
import java.io.*;

/**
 *
 * @author Alunos-i221-16
 */
import java.util.Properties;
public class MultiServerThread extends Thread {
    private Socket socket = null;
    private final Properties props;
 
    public MultiServerThread(Socket socket, Properties props) {
        super("MultiServerThread");
        this.socket = socket;
        this.props = props;
    }
 
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
            String inputLine, reply = null;
    
            Protocol p = new Protocol();
            
            if((inputLine = in.readLine()) != null) {
                reply = p.ProtocolDecode(inputLine);
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
