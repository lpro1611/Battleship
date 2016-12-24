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
    public static Map <Integer, Challenge> ChallengeList = new HashMap<>();
    
    
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
        
        // while user is logged in
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
    
    public static String setChallenge(int playerId1, int playerId2) {
        //o playerId1 pode funcionar como chave pois um utilizador apenas pode fazer um challenge de cada vez
        //não esquecer de retirar esta entrada da lista depois do challenge se resolvido
        ChallengeList.putIfAbsent(playerId1, new Challenge(playerId1, playerId2));
        
        
        while (ChallengeList.get(playerId1).getState().equals("wait")) {/* do nothing */}
        
        // se demorou muito tempo sem resposta envia timeout
        //return "timeout";
        
        if (ChallengeList.get(playerId1).getState().equals("accept")) {
            //começar jogo
            
        }
        
        return ChallengeList.get(playerId1).getState();
    }
    
    public static void receiveChallenge() {}
    public static void replyChallenge() {}
    
}
