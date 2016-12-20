package communicationserver;

import java.util.Properties;
import java.net.*;
import java.io.*;

/**
 * Deals with generating all the sockets to communicate with 
 * multiple clients simultaneously.
 * 
 * @author Alunos-i221-16
 */
public class MultiServer {
    
    /**
     * Class Constructor
     */
    public MultiServer(){}
    
    /**
     * Develops multiple threads, each one communicates with a client.
     * <p>
     * This main method listens to the port of the server. 
     * When it detects a client it generates a thread to communicate with him.
     * 
     * @param  args         command line arguments (not used)
     * @throws IOException  if failed to handle socket
     */
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
