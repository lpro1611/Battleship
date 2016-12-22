/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;

/**
 *
 * @author Rafeal Kraemer
 */

public class ChatServer { 
    public static void main(String[] args) throws IOException {
        try{
            final int PORT = 6677; 
            ServerSocket server = new ServerSocket (PORT);
            System.out.println("Waiting for clients ...");
            
            while(true){
                Socket s = server.accept();
                
                System.out.println("Client connected " + s.getLocalAddress().getHostName());
                
                ClientThread chat = new ClientThread(s);
                Thread t = new Thread(chat);
                t.start();
            }
            
        }
        catch (Exception e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }
}