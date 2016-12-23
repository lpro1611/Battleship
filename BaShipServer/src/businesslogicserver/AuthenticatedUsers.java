package businesslogicserver;

import exceptions.NotFoundException;
import java.sql.SQLException;
import java.util.Map;

/**
 *  Saves a list with all active users
 *  and has some methods to interact with that list
 * 
 * @author Diogo Dinis
 */
public class AuthenticatedUsers {
    public static Map <Integer, Authenticated> authenticatedList;
    
    
    public AuthenticatedUsers () {}
    
    public static void add (int id) throws SQLException, NotFoundException {
        authenticatedList.put(id, new Authenticated(id));
        
        for (Map.Entry<Integer, Authenticated> entry : authenticatedList.entrySet()) {
            System.out.println((entry.getValue()).name);
        }
    }
    
    public static void remove() {}
    public static void receiveChallenge() {}
    public static void sendChallenge() {}
    public static void replyChallenge() {}
    public static void menuChallenge() {}
    public static void addSocket() {}
}
