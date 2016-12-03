package businesslogicclient;

/**
 * Represents an authenticated user which has a name 
 * and a unique number identifier.
 * @author Diogo Recharte
 */
public class Authenticated {
    private static int id;
    private static String username = "";
    
    /**
     * Class constructor specifying id number and username of
     * the authenticated user.
     * 
     * @param id            authenticated's unique identifier number
     * @param username      authenticated's name
     */
    public Authenticated(int id, String username){
        Authenticated.id = id;
        Authenticated.username = username;
    }
    
    /**
     * Returns the authenticated user's id number.
     * <p>
     * This method always returns successfully.
     * 
     * @return  authenticated user's unique identifier number
     */
    public static int getID(){
        return id;
    }
    
    /**
     * Returns the authenticated user's name.
     * <p>
     * This method always returns successfully.
     * 
     * @return  authenticated user's name
     */
    public static String getUsername(){
        return username;
    }
    
    /**
     * Sets the authenticated user's id number.
     *
     * @param id    authenticated user's unique identifier number
     */
    public static void setID(int id){
        Authenticated.id = id;
    }
    
    /**
     * Sets the authenticated user's name.
     * 
     * @param username  authenticated user's name
     */
    public static void setUsername(String username){
        Authenticated.username = username;
    }
    
}
