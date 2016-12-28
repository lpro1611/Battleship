/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

import dataacess.DbGame;
import java.sql.SQLException;
import java.util.*;


/**
 *Hold the information from all the games currently played.
 * Processes thecreationa an finalization of a game
 * 
 * @author CPU TOSH
 */
public class Game {
    /**
     * Gamelsist : Map of all currently played games
     */
    
    public static Map <Integer, GameState> GameList = new HashMap<>();
    //private static int id = 0;
    
    public Game() {}
    /**
     * This method handles the initilization of new game
     * <p>
     * This method receives the id numbers from the players. 
     * It then generates a random id to identify the game.
     * With these values it generates a new GameState, 
     * and adds it to the list o f currently playd games. Lastly it saves
     * the game information to the database, 
     * and sends a message to the players, 
     * informing the that the game has started.
     * 
     * @param player1Id id number from player 1
     * @param player2Id id number from player 2
     */
    public static void createGame(int player1Id, int player2Id) {
        Random idGenerator = new Random();
        int id = idGenerator.nextInt();
        
        while(GameList.containsKey(id) )
            id = idGenerator.nextInt();
        
        GameList.put(id, new GameState(id, player1Id, player2Id));        
        try{
            DbGame.createGame(player1Id, player2Id);
        } catch(SQLException e) {
            System.out.println("Error saving ship" + e);
        }
    }
    
    /**
     * This method handles an attack by a player and its consequences
     * <p>
     * This method receives the identifaction numbers for the game an the attacking player.
     * It also receives the attacked position. 
     * The method then calls the GameState method 
     * that processes the attacks.
     * If this method returns 6 critical hits, 
     * then the attacking player has won the game. 
     * The method saves this information
     * in the database, and sends end game message to the players.
     * 
     * @param gameId id number of game
     * @param playerId id number of attacking player
     * @param x horizontal board position 
     * @param y vertical board position
     * @return ok message to inform player
     */
    public static String attack(int gameId, int playerId, int x, int y) {
        int hits = GameList.get(gameId).attack(playerId, x, y);
        
        if (hits == 6) {
            if (playerId == (GameList.get(gameId).getPlayer1Id())) {
                //Protocol.endGame(..) Para ambos jogaores Player 1 victorious
                try {
                    DbGame.setGameWinner(playerId, gameId);
                } catch (SQLException e) {
                    System.out.println("Error saving ship" + e );
                }
                
                GameList.remove(gameId);
            } else {
                //Protocol.endGame(..) Para ambos jogaoresPlayer 2 victorious
                try {
                    DbGame.setGameWinner(playerId, gameId);
                } catch(SQLException e) {
                    System.out.println("Error saving ship" + e );
                }
                
                GameList.remove(gameId);
               
            }      
        }
        return "ok"; //se necessario
    }
}
    

