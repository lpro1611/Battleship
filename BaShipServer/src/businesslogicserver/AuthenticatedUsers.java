package businesslogicserver;

import communicationserver.SendMessageSocket;
import exceptions.NotFoundException;
import java.io.PrintWriter;
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
    public static MatchMakerThread matchmakerthread;
    public static Integer lastPlayerRequest;
    
    
    public AuthenticatedUsers () {}
    
    /**
     * This method adds another User to the User list
     * 
     * @param id    identification number of player
     * @return      confirmation
     * 
     * @throws SQLException
     * @throws NotFoundException 
     */
    public static boolean add (int id) throws SQLException, NotFoundException {
        return authenticatedList.putIfAbsent(id, new Authenticated(id)) != null;
    }
    
    /**
     * This method removes a player from the authenticted users list
     * <p>
     * 
     * After a player is removed the method waits ten second
     * to second socket closes itself.
     * 
     * @param userId identification number of user to remove
     */
    public static void remove(int userId) {
        long timeoutTime = 10 * 1000; //10 segundos
        
        authenticatedList.remove(userId);
        long startTime = System.currentTimeMillis();
        while (true) {
            /* do nothing */
            if (System.currentTimeMillis() >= (startTime + timeoutTime)) {
                break;
            }
        } 
    }
    
    /**
     * This method connects an individual Socket to a user through an
     * output stream.
     * <p>
     * This method gives the user a stream to communicate with a Socket. 
     * This Socket is used by the server to send messages to 
     * the declared user as an argument. The Socket is left open, 
     * while the user is active. The moment the user is off the list 
     * it sends an exit message to the Socket.
     * 
     * @param userId    identification numbe of user to be given 
     * @param out       1stream given to the user
     */
    public static void addSocket(int userId, PrintWriter out) {
        authenticatedList.get(userId).setSocket(out);
        
        new SendMessageSocket("socket#ok", out).start();
        
        // while user is logged in
        while (authenticatedList.containsKey(userId)) {/* do nothing */}
        new SendMessageSocket("exit", out).start();
    }
    
    /**
     * Send the active players to a specific client to create the chllenge menu.
     * 
     * @param userId    user's identifier
     * @return          list with active users.
     */
    public static String menuChallenge(int userId) {
        String usersString = null;
        
        for (Map.Entry<Integer, Authenticated> entry : authenticatedList.entrySet()) {
            if ((entry.getValue()).getId() != userId) {
                if (usersString == null)
                    usersString = "#";
                else
                    usersString += "#";
                usersString += (entry.getValue()).getId();
                usersString += "#";
                usersString += (entry.getValue()).getName();
            }
        }        
        
        return usersString;
    }
    
    /**
     * This method is called to send a challenge to another User
     * <p>
     * The challenging and challenges user id numbers are given as arguments
     * 
     * @param player1Id     challenger's identifier
     * @param player2Id     guest's identifier
     * @return              reply of guest, or timeout
     */
    public static String setChallenge(int player1Id, int player2Id) {
        long timeoutTime = 2 * 60 * 1000; //2 minutos 
        
        if (authenticatedList.get(player2Id).getCurrentGameId() != -1) {
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
                ChallengeList.remove(player1Id);
                return "timeout";
            }
        }
        
        String reply = ChallengeList.get(player1Id).getState();
        
        if (reply.equals("accept")) {
            int gameId = Game.createGame(player1Id, player2Id);
            authenticatedList.get(player1Id).setCurrentGameId(gameId);
            authenticatedList.get(player2Id).setCurrentGameId(gameId);
            reply += "#" + gameId;
        }
        
        ChallengeList.remove(player1Id);
        
        return reply;
    }
    
    /**
     * This method is called to reply to a challenge
     * 
     * @param player1Id guest's identifier
     * @param message   reply of the guest    
     * @return          message to previous layer
     */
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
    
    /**
     * This method adds a player to a list of users currently searching for game.
     * <p>
     * If the thread that handles the matchmaking
     * isn´t already opened, this method opens it. Else it only
     * adds the player to the list.
     * 
     * @param PlayerID identification number of player that is searching for a game
     */
    public static void playNow(Integer PlayerID){
        if(matchmakerthread.isAlive()==false){
            matchmakerthread.run();
        }
      
        matchmakerthread.Players.add(PlayerID);
        return; 
    }
}
