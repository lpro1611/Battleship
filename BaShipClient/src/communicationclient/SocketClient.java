package communicationclient;

import java.io.*;
import java.net.*;
/**
 * Communicates with the server.
 * 
 * @author Alunos-i221-16
 */
public class SocketClient {
    Socket bsSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    
    /**
     * Class Constructor
     */
    public SocketClient(){}
    
   /**
    * Opens a communication link with the server through a TCP/IP socket.
    * <p>
    * This method instantiates a TCP/IP socket with the server 
    * specifications. Afterwards it instantiates the input and output 
    * streams for the communications.
    * 
    * @throws UnknownHostException      if the server host is unknown
    * @throws IOException               if failed to create the streams
    */
    public void openCom() throws UnknownHostException, IOException{
        bsSocket = new Socket("localhost", 4020);
        //System.out.println("Connected to server.");
        out = new PrintWriter(bsSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(bsSocket.getInputStream()));
    }
    
    /**
     * Writes a string into the output stream.
     *
     * @param write     string to write in the output stream
     */
    public void write(String write){
        out.println(write);
    }
    
    /**
     * Reads a string from the input stream.
     * <p>
     * This method returns the String, read from the input stream. 
     * If reading failed it throws an Exception.
     * 
     * @return                  string read from the input stream
     * @throws  IOException     if failed to read the stream
     */
    public String read() throws IOException{
        return in.readLine();
    }
    
    /**
     * Closes the communication link with the server
     * <p>
     * This method closes the socket and streams instantiated in openCom.
     * 
     * @throws IOException      if failed to close the streams or the socket
     */
    public void closeCom() throws IOException{
        if (out != null)
            out.close();
        if (in != null)
            in.close();
        if (bsSocket != null)
            bsSocket.close();
    }
}
