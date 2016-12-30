/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationserver;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author diogo
 */
public class MultiServer extends Thread {
    private final int port;
    private final boolean listening = true;
    private ServerSocket serverSocket = null;
    
    public MultiServer(int port) {
        super("MultiServer");
        this.port = port;
    }
    /**
     * Develops multiple threads, each one communicates with a client.
     * <p>
     * This main method listens to the port of the server. 
     * When it detects a client it generates a thread to communicate with him.
     */
    @Override
    public void run() {
         try {
             serverSocket = new ServerSocket(port);
        } catch (IOException e) {
             System.err.println("Could not listen on port: " + port + ".");
             System.exit(-1);
        }
        
        // preciso de parar todos os threads que comecei
         
        try {
            while (listening) {
                new ReceiveMessageSocket(serverSocket.accept()).start();  
            }
        } catch (IOException e) {
            System.err.println("Could not use port: " + port +".");
            System.exit(-1);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close I/O with port: " + port +" properly.");
                System.exit(-1);
            }
        }
    }
}
