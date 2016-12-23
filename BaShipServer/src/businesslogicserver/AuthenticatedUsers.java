package businesslogicserver;

import communicationserver.SendMessageSocket;
import exceptions.NotFoundException;
import java.net.Socket;
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
        return authenticatedList.putIfAbsent(id, new Authenticated(id)) != null;
    }
    
    public static void remove(int userId) {
        authenticatedList.remove(userId);
    }
    
    public static void addSocket(int userId, Socket socket) {
        authenticatedList.get(userId).setSocket(socket);
        
        new SendMessageSocket("socket#ok", socket).start();
        
        // while there logged in user
        while (authenticatedList.containsKey(userId)) {/* do nothing */}
    }
    
    public static String menuChallenge(int userId) {
        String list = null;
        
        for (Map.Entry<Integer, Authenticated> entry : authenticatedList.entrySet()) {
            if ((entry.getValue()).getId() != userId) {
                list += "#";
                list += (entry.getValue()).getId();
                list += "#";
                list += (entry.getValue()).getName();
            }
        }        
        
        return list;
    }
    
    public static void receiveChallenge() {}
    public static void sendChallenge() {}
    public static void replyChallenge() {}
    
}
