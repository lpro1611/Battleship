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
public class MultiServerThread extends Thread {
    private Socket socket = null;
 
    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }
 
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
            String inputLine;
    
            Protocol p = new Protocol();
            
            if((inputLine = in.readLine()) != null) {
                p.ProtocolDecode(inputLine);
            }
             
            out.close();
            in.close();
            socket.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
