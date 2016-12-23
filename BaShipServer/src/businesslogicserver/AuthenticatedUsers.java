package businesslogicserver;

import exceptions.NotFoundException;
import java.sql.SQLException;
import java.util.*;

/**
 *  Saves a list with all active users
 *  and has some methods to interact with that list
 * 
 * @author Diogo Dinis
 */
public class AuthenticatedUsers {
    public static Map <Integer, Authenticated> authenticatedList = new HashMap<>();
    
    
    public AuthenticatedUsers () {}
    
    public static boolean add (int id) throws SQLException, NotFoundException {
        
        /* // Debugging
        System.out.println("lista de jogadores ativos");
        for (Map.Entry<Integer, Authenticated> entry : authenticatedList.entrySet()) {
        System.out.println((entry.getValue()).getName());
        }
         */        

        return authenticatedList.putIfAbsent(id, new Authenticated(id)) != null;
    }
    
    public static void remove() {}
    public static void receiveChallenge() {}
    public static void sendChallenge() {}
    public static void replyChallenge() {}
    public static void menuChallenge() {}
    public static void addSocket() {}
}
