/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package communicationserver;

import java.util.Properties;
import java.net.*;
import java.io.*;

/**
 *
 * @author Alunos-i221-16
 */
public class MultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        Properties props = new Properties();
        
        props.setProperty("dburl", "jdbc:postgresql://dbm.fe.up.pt/lpro1611");
        props.setProperty("dbpass", "G!7367hxf");
        props.setProperty("dbuser", "lpro1611");
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch ( ClassNotFoundException e) {
            System.out.println("Can't found postgres Driver");
            e.printStackTrace();
        }
        
        try {
             serverSocket = new ServerSocket(4020);
        } catch (IOException e) {
             System.err.println("Could not listen on port: 4020.");
             System.exit(-1);
        }
 
        while (listening) {
            new MultiServerThread(serverSocket.accept(), props).start();
        }
 
        serverSocket.close();
    }
}
