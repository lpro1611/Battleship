/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationclient;

import java.io.*;
import java.net.*;
/**
 *Class that comunciates with the server on the client side
 * <p>
 * @author Alunos-i221-16
 * @version 0.1 
 */
public class SocketClient {
    Socket bsSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
   /**
    * Opens a communication link with the server through a TCP/IP Socket
    * <p>
    * This method instatiates a TCP/IP Socket with the server specifications. 
    * Afterwards it instantiates the input and output streams for the comunications 
    * <p>
    * @throws UnknownHostException exception in case the server host is unknown
    * @throws IOException exception in case of failure creating the streams
    */
    public void openCom() throws UnknownHostException, IOException{
        bsSocket = new Socket("localhost", 4020);/* aqui é necessário por o hostname do servidor */
        //System.out.println("Connected to server.");
        out = new PrintWriter(bsSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(bsSocket.getInputStream()));
    }
    
    /**
     * Writes a String into the output stream
     * <p>
     * This method recieves a String and then writes in the output String.
     * This method always returns
     * <p>
     * @param write String to write in the output stream
     */
    
    public void write(String write){
        out.println(write);
    }
    /**
     * Reads a string from the input stream
     * <p>
     * This method returns the String, read from the input stream. 
     * If the reading fail it throws an Exception
     * <p>
     * @return  the Sring read from the input stream
     * @throws IOException exception in case of failure reading the stream
     */
    public String read() throws IOException{
        return in.readLine();
    }
    /**
     * Closes the communication link with the server
     * <p>
     * This method closes the Socket and streams instantiated in openCom.
     * This method returns succesfully unles there is an error in the closing.
     * <p>
     * @throws IOException exception in case the closing fails
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
