package businesslogicclient;

/**
 *
 * @author diogo recharte
 */
public class Authenticated {
    private static int id;
    private static String username = "";
    /**
     * 
     * @param id
     * @param username 
     */
    public Authenticated(int id, String username){
        Authenticated.id = id;
        Authenticated.username = username;
    }
    /**
     * 
     * @return 
     */
    public static int getID(){
        return id;
    }
    /**
     * 
     * @return 
     */
    public static String getUsername(){
        return username;
    }
    /**
     * 
     * @param id 
     */
    public static void setID(int id){
        Authenticated.id = id;
    }
    /**
     * 
     * @param username 
     */
    public static void setUsername(String username){
        Authenticated.username = username;
    }
    
}
