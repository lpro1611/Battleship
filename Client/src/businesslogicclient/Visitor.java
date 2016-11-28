package businesslogicclient;

import communicationclient.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author diogo recharte
 */
public class Visitor {
    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    public static boolean login(String username, char[] password){
        Protocol.validateLogin(username, encryptPassword(String.valueOf(password)));
        if (ReplyFromServer.getMessage().equals("ok")){
            Authenticated.setID(ReplyFromServer.getID());
            Authenticated.setUsername(username);
            return true;
        }
        else return false;
    }
    /**
     * 
     * @param email
     * @param username
     * @param pass
     * @param confirmPass
     * @param termsAccepted
     * @return 
     */
    public static String register(String email, String username, char[] pass, char[] confirmPass, boolean termsAccepted){
        String password = encryptPassword(String.valueOf(pass));
        String confirmPassword = encryptPassword(String.valueOf(confirmPass));
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
            Authenticated.setUsername(username);
            return "ok";
        }
        
        else return ReplyFromServer.getMessage();
    }
    /**
     * 
     * @param password
     * @return 
     */
    private static String encryptPassword(String password){
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
    /**
     * 
     * @param email
     * @return 
     */
    private static boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    
}
