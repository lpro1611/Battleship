/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicclient;

import communicationclient.*;
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
    
    public LoginBL(String username, char[] password){
        this.username = username;
        this.password = encryptPassword(String.valueOf(password));
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
        Protocol.validateLogin(username, password);
        if (ReplyFromServer.getMessage().equals("ok")){
            Authenticated.setID(ReplyFromServer.getID());
            return true;
        }
        else return false;
        
    }
}
