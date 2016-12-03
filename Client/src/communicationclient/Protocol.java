package communicationclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Implements the communication protocol between
 * the server and the client.
 * @author Diogo Recharte
 */
public class Protocol {
    private static final String OPCODE1 = "login";
    private static final String OPCODE2 = "register";
    
    /**
     * Class Constructor
     */
    public Protocol(){}
    
    /**
     * Prepares and sends the message to the server, to validate the 
     * login data.
     * <p>
     * This method receives a username and a password and combines them 
     * with the login opcode to generate a login message for the server.
     * Afterwards it sends the message, awaits the response from the 
     * server and passes it to be interpreted.
     * 
     * @param username  username attempting to log in
     * @param password  password attempting to log in
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
    * Prepares and sends the message to the server, to validate the 
    * register data.
    * <p>
    * This method receives a username, a password and an email address 
    * and combines them with the register opcode to generate a register 
    * message for the server. Afterwards it sends the message, awaits 
    * the response from the server and passes it to be interpreted.
    * <p>
    * @param email      email address to register
    * @param username   username to register
    * @param password   password to register
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
     * Interprets the messages received from the server.
     * <p>
     * This method receives the message sent by the server as well as the 
     * expected opcode. It then checks the validity of this reply and
     * saves the important data to be used by the business logic.
     * 
     * @param input         message received from the server
     * @param operation     opcode to validate the message
     */
     private static void decodeReply(String input, String operation) {
        String opcode[] = input.split("#");
        if (opcode[0].equals(operation)){
            ReplyFromServer.setMessage(opcode[1]);
            if (opcode[1].equals("ok"))
                ReplyFromServer.setID(Integer.parseInt(opcode[2]));
        }
        else {
            ReplyFromServer.setMessage("Error communicating with server");
            System.out.println("Error communicating with server");
        }
    }
    
}
