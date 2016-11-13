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
 *
 * @author diogo
 */
public class Protocol {
    private static final String OPCODE1 = "login";
    private static final String OPCODE2 = "register";
    
    
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
     private static void decodeReply(String input, String operation) {
        String opcode[] = input.split("#");
        ReplyFromServer.setMessage(opcode[1]);
        if (opcode[1].equals(input))
            ReplyFromServer.setID(Integer.parseInt(opcode[2]));
    }
    
}
