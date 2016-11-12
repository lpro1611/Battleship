/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import clientsocket.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class LoginBL {
    private String username = "";
    private String password = "";
    
    public LoginBL(String username, String password){
        this.username = username;
        this.password = encryptPassword(password);
    }
    
    private String encryptPassword(String password){
        byte[] bytesOfMessage = null;
        try {
            bytesOfMessage = password.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException");
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
             System.out.println("NoSuchAlgorithmException");
        }
        byte[] thedigest = md.digest(bytesOfMessage);
        
        String pass="";
        for (byte b: thedigest)
            pass=pass.concat(String.format("%02X", b));
        return pass;
    }
    
    public boolean isValid(){
        boolean retValue=false;
        CommunicationSocket cSocket = new CommunicationSocket();
        
        try{ 
            cSocket.openCom();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: bloco-cinza.");
            System.exit(1);
        }
        
        
        retValue=cSocket.validateLogin(username, password); //true or false dependendo da resposta do server
        
        
        try{
            cSocket.closeCom();
        } catch(IOException e){
            System.out.println("Couldn't close socket");
        }
        
        return retValue;
    }
}
