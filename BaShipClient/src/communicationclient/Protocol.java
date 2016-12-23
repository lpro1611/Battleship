package communicationclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;


/**
 * Implements the communication protocol between
 * the server and the client.
 * @author Diogo Recharte
 */
public class Protocol {
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String GAME = "game";
    
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
    public static int validateLogin(String username, String password){
        SocketClient cSocket = new SocketClient();
        String inputLine;
        String[] reply;
        int userID=-1;
        try{ 
            cSocket.openCom();
            
            cSocket.write(LOGIN + "#" + username + "#" + password);
            
            inputLine=cSocket.read();
            System.out.println(inputLine);
            reply=decodeReply(inputLine, LOGIN);
            if(reply[0].equals("ok"))
                userID = Integer.parseInt(reply[1]);
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        } finally{
            try {
                cSocket.closeCom();
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to gnomo.");
            }
        }
        return userID;
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
    public static int validateRegister(String email, String username, String password){
        SocketClient cSocket = new SocketClient();
        String inputLine;
        String[] reply;
        int userID=-1;
        
        try{ 
            cSocket.openCom();
            cSocket.write(REGISTER + "#" + email + "#" +  username + "#" + password);
            inputLine=cSocket.read();
            System.out.println("reg: " + inputLine);
            reply=decodeReply(inputLine, REGISTER);
            if(reply[0].equals("ok"))
                userID = Integer.parseInt(reply[1]);
            else if (reply[0].equals("duplicated"))
                userID = -2;
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        } finally{
            try {
                cSocket.closeCom();
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to gnomo.");
            }
        }
        
        return userID;
        
    }
    public static void findGame(int userID){
        SocketClient cSocket = new SocketClient();
        String inputLine;
        
        try{ 
            cSocket.openCom();
            
            cSocket.write(GAME + "#" + "create" + "#" + userID);
            
            inputLine=cSocket.read();
            
            decodeReply(inputLine, GAME);
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        } finally{
            try {
                cSocket.closeCom();
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to gnomo.");
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
     private static String[] decodeReply(String input, String operation){
        String opcode[] = input.split("#");
        if (opcode[0].equals(operation)){
            return Arrays.copyOfRange(opcode, 1, opcode.length);
        }
        else {
            System.out.println("Error communicating with server");
            return null;                                                // secalhar mudar isto
        }
    }
    
}
