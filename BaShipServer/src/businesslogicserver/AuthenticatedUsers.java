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
        String usersString = null;
        
        for (Map.Entry<Integer, Authenticated> entry : authenticatedList.entrySet()) {
            if ((entry.getValue()).getId() != userId) {
                usersString += "#";
                usersString += (entry.getValue()).getId();
                usersString += "#";
                usersString += (entry.getValue()).getName();
            }
        }        
        
        return usersString;
    }
    
    public static String setChallenge(int player1Id, int player2Id) {
        //o playerId1 pode funcionar como chave pois um utilizador apenas pode fazer um challenge de cada vez
        //não esquecer de retirar esta entrada da lista depois do challenge se resolvido
        ChallengeList.putIfAbsent(player1Id, new Challenge(player1Id, player2Id));
        // ver se o jogador está  jogar ou não
        String message = "invite#" + player1Id + "#" + authenticatedList.get(player1Id).getName();
        
        //new SendMessageSocket.(message , authenticatedList.get(player2Id).getSocket()).start();
        
        
        while (ChallengeList.get(player1Id).getState().equals("wait")) {/* do nothing */}
        
        // se demorou muito tempo sem resposta envia timeout
        //return "timeout";
        
        if (ChallengeList.get(player1Id).getState().equals("accept")) {
            //começar jogo
            
        }
        
        return ChallengeList.get(player1Id).getState();
    }
    
    public static void receiveChallenge() {}
    public static void replyChallenge() {}
    
}
