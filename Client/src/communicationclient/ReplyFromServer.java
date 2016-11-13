/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationclient;

/**
 *
 * @author diogo
 */
public class ReplyFromServer {
    
    private static String message;
    private static int id;
    
    public static String getMessage(){
        return message;
    }
    
    public static int getID(){
        return id;
    }
    
    public static void setMessage(String message){
        ReplyFromServer.message = message;
    }
    
    public static void setID(int id){
        ReplyFromServer.id = id;
    }
    
}
