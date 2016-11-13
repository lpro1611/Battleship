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

/**
 *
 * @author diogo
 */
public class RegisterBL {
    private String email="";
    private String username = "";
    private String password = "";
    private String confirmPassword="";
    private boolean termsAccepted=false;
    
    public RegisterBL(String email, String username, char[] password, char[] confirmPassword, boolean termsAccepted){
        this.email = email;
        this.username = username;
        this.password = encryptPassword(String.valueOf(password));
        this.confirmPassword = encryptPassword(String.valueOf(confirmPassword));
        this.termsAccepted = termsAccepted;
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
     private boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    
    public String status(){
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()|| confirmPassword.isEmpty())
            return "Fill in all fields!";
        if(!isValidEmailAddress(email))
            return "Insert a valid email address";
        if(username.contains("#")||password.contains("#"))
            return "Caracter \"#\" is not allowed";
        if (!termsAccepted)
            return "Please accept the terms and conditions!";
        if (!password.equals(confirmPassword))
            return "Passwords don't match!";
        
        
        Protocol.validateRegister(email, username, password); //true or false dependendo da resposta do server
        
        if (ReplyFromServer.getMessage().equals("ok")){
            Authenticated.setID(ReplyFromServer.getID());
            return "ok";
        }
        
        else return ReplyFromServer.getMessage();
    }
}
