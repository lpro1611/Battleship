package businesslogicclient;

/**
 *
 * @author diogo
 */
public class Player {
    private static int id;
    private static String username = "";
    
    /**
     * Returns the player's id number.
     * <p>
     * This method always returns successfully.
     * 
     * @return  player's unique identifier number
     */
    public static int getID(){
        return id;
    }
    
    /**
     * Returns the player's name.
     * <p>
     * This method always returns successfully.
     * 
     * @return  player's name
     */
    public static String getUsername(){
        return username;
    }
    
    /**
     * Sets the player's id number.
     *
     * @param id    player's unique identifier number
     */
    public static void setID(int id){
        Player.id = id;
    }
    
    /**
     * Sets the player's name.
     * 
     * @param username  player's name
     */
    public static void setUsername(String username){
        Player.username = username;
    }
    
}
