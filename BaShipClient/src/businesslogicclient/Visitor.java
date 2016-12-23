package businesslogicclient;

import communicationclient.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a user that isn't yet authenticated or registered.
 * @author Diogo Recharte
 */
public class Visitor {
    
    /**
     * Class Constructor
     */
    public Visitor(){}
    
    /**
     * Logs in the visitor.
     * <p>
     * Calls the communication protocol to access the server and check 
     * if the parameters entered are valid.
     * <p>
     * If successful the user will be given the authenticated user privileges. 
     * 
     * @param username  the user's name
     * @param password  the user's password
     * @return          <code>true</code> if successful; 
     *                  <code>false</code> otherwise.
     */
    public static boolean login(String username, char[] password){
        int userID = Protocol.validateLogin(username, encryptPassword(String.valueOf(password)));
        if (userID > 0){
            
            Authenticated.setID(userID);
            Authenticated.setUsername(username);
            System.out.println("id: " + Authenticated.getID());
            return true;
        }
        else return false;
    }
    /**
     * Registers a new user
     * <p>
     * Verifies that all parameters are correctly filled. Calls the
     * communication protocol in order to access the server and 
     * register the user in the data base.
     * <p>
     * If successful the user will be given the authenticated user privileges. 
     * 
     * @param email             e-mail address of the user
     * @param username          name of the user
     * @param pass              password for the user's login
     * @param confirmPass       password for the user's login
     * @param termsAccepted     terms accepted by the user
     * @return                  message representative of success
     *                          or the errors that occurred
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
        
        
        int userID = Protocol.validateRegister(email, username, password);
        
        if (userID == -2){
            return "Username not available";
        }
        else if (userID == -1){
            return "Error accessing server";
        }
        
        else if (userID > 0){ 
            Authenticated.setID(userID);
            Authenticated.setUsername(username);
            System.out.println("id: " + Authenticated.getID());
            return "ok";
        }
        else
            return "Unknown Error";
    }

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

    private static boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    
}
