/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Properties;


/**
 *Class that implements the protocol between the server and the clinet on the client side.
 * and client on the client side.
 * @author diogo
 */
public class Protocol {
    private static final String OPCODE1 = "login";
    private static final String OPCODE2 = "register";
    /**
     * Prepares and sends the message to the server, to validate the login data.
     * <p>
     * This method receives a username and a pasword, and 
     * combines them with the login opcode to generate a login message for the
     * server. Aferward it sends the message, awaits the response from the server,
     * and passes it to be 
     * interpreted.
     * <p>
     * @param username String that holds the received username
     * @param password String that holds the received password
     */
    
    public static void validateLogin(String username, String password){
        SocketClient cSocket = new SocketClient();
        String inputLine;
        
        try{ 
            cSocket.openCom();
            
            cSocket.write(OPCODE1 + "#" + username + "#" + password);
            
            inputLine=cSocket.read();
            
            decodeReply(inputLine, OPCODE1);
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: bloco-cinza.");
        } finally{
            try {
                cSocket.closeCom();
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to: bloco-cinza.");
            }
        }
        
    }
    
   /**
    * Prepares and sends the message to the server, to validate the register data.
    * <p>
    * This method receives a username, a pasword, an email and 
    * combines them with the register opcode to generate a register message for the
    * server. All of these data are Strings. 
    * Aferward it sends the message, and awaits the response from the server,
    * and passes it to be interpreted.
    * <p>
    * @param email String that holds the email to register
    * @param username  String that holds the username to register
    * @param password   String that holds the password to register
    */
    public static void validateRegister(String email, String username, String password){
        SocketClient cSocket = new SocketClient();
        String inputLine = "";
        try{ 
            cSocket.openCom();
            cSocket.write(OPCODE2 + "#" + email + "#" +  username + "#" + password);
            inputLine=cSocket.read();
            
            decodeReply(inputLine, OPCODE2);
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: bloco-cinza.");
        } finally{
            try {
                cSocket.closeCom();
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to: bloco-cinza.");
            }
        }
        
    }
    /**
     * Interprets the messages received from  the server
     * <p>
     * This method receives the message from the server and the valid opcode.
     * It then checks the validity of the reply from the server, with the received opcode.
     * Afterwards it saves the important data to be used by the business logic.
     * <p>
     * @param input String tha holds the message received
     * @param operation  String that hold the opcode to validate the message
     */
     private static void decodeReply(String input, String operation) {
        String opcode[] = input.split("#");
        ReplyFromServer.setMessage(opcode[1]);
        if (opcode[1].equals(input))
            ReplyFromServer.setID(Integer.parseInt(opcode[2]));
    }
    
}
