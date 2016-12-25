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
        long timeoutTime = 2 * 60 * 1000; //2 minutos 
        
        if (authenticatedList.get(player2Id).getInGame()) {
             return "reject"; // the player is playing a game
         }

        //o playerId1 pode funcionar como chave pois um utilizador apenas pode fazer um challenge de cada vez
        ChallengeList.putIfAbsent(player1Id, new Challenge(player1Id, player2Id));
        
        // message to the player 2
        String message = "invite#" + player1Id + "#" + authenticatedList.get(player1Id).getName();
        new SendMessageSocket(message , authenticatedList.get(player2Id).getSocket()).start();
        
        long startTime = System.currentTimeMillis();
        while (ChallengeList.get(player1Id).getState().equals("wait")) {
            /* do nothing */
            if (System.currentTimeMillis() >= (startTime + timeoutTime)) {
                return "timeout";
            }
        }
        
        String reply = ChallengeList.get(player1Id).getState();
        ChallengeList.remove(player1Id);
        
        if (reply.equals("accept")) {
            int gameId = Game.createGame(player1Id, player2Id); //isto devia de retornar o id do jogo
            authenticatedList.get(player1Id).setCurrentGameId(gameId);
            authenticatedList.get(player2Id).setCurrentGameId(gameId);
            reply += gameId;
        }
        
        return reply;
    }
    
    public static String replyChallenge(int player1Id, String message) {
        Integer gameId;
        long timeoutTime = 1 * 60 * 1000; //1 minuto 
        String reply = "ok";
        
        if (!ChallengeList.containsKey(player1Id)) {
            return "error";
        }
        
        ChallengeList.get(player1Id).setState(message);
        
        if (message.equals("accept")) {
            long startTime = System.currentTimeMillis();
            while ((gameId = authenticatedList.get(player1Id).getCurrentGameId()) == -1) {
                /*do nothing*/
                if (System.currentTimeMillis() >= (startTime + timeoutTime)) {
                    return "error";
                }
            }
            
            reply = gameId.toString();
        }
        
        return reply;
    }
    
}
