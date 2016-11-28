/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationclient;

/**
 *Class that stores the responses from the server
 * <p>
 * @author diogo
 */
public class ReplyFromServer {
    
    private static String message;
    private static int id;
    /**
     * Returns the last saved message from the server.
     * The message is a String.
     * This method always returns successfuly 
     * <p>
     * @return  returns a string, which was the las savd message from the server
     */
    public static String getMessage(){
        return message;
    }
    /**
     * Returns the last saved id from the server.
     * The id is an integer.
     * This method always returns successfuly .
     * <p>
     * @return  returns an integer, which was the last saved integer from the server.
     *
     */
    public static int getID(){
        return id;
    }
    /**
     * Saves a received message.
     * The message is a String
     * <p>
     *
     * @param message String that is saved
     */
    public static void setMessage(String message){
        ReplyFromServer.message = message;
    }
    /**
     * Saves a received id.
     * The id is an integer.
     * <p>
     *
     * @param id Integer that is saved
     */
    public static void setID(int id){
        ReplyFromServer.id = id;
    }
    
}
