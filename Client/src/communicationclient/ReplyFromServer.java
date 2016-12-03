package communicationclient;

/**
 * Stores the responses from the server.
 * @author Diogo Recharte
 */
public class ReplyFromServer {
    
    private static String message;
    private static int id;
    
    /**
     * Class Constructor
     */
    public ReplyFromServer(){}
    
    /**
     * Returns the last saved message received from the server.
     * <p>
     * This method always returns successful.
     *
     * @return  last saved message received from the server
     */
    public static String getMessage(){
        return message;
    }
    
    /**
     * Returns the last saved id received from the server.
     * <p>
     * This method always returns successful.
     * 
     * @return  last saved user id received from the server
     */
    public static int getID(){
        return id;
    }
    
    /**
     * Saves a received message.
     *
     * @param message   message received from the server
     */
    public static void setMessage(String message){
        ReplyFromServer.message = message;
    }
    
    /**
     * Saves a received user id.
     *
     * @param id    user's id received from the server
     */
    public static void setID(int id){
        ReplyFromServer.id = id;
    }
    
}
