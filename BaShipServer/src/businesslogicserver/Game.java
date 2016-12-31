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
     * @return          game's identifier
     */
    public static int createGame(int player1Id, int player2Id) {
        Random idGenerator = new Random();
        int id;
        
        do {
            id = idGenerator.nextInt();
        } while (GameList.containsKey(id));
        
        GameList.put(id, new GameState(id, player1Id, player2Id));
        
        try {
            DbGame.createGame(player1Id, player2Id);
        } catch (SQLException e) {
            System.out.println("Error saving ship (Isto não é um barco!)" + e);
        }
        
        return id;
    }
    
    public static String playerReady(int gameId, int playerId) {
        while (GameList.get(gameId).playerReady(playerId).equals("wait")) {
            /*do nothing*/
        }
        
        if (GameList.get(gameId).getNextPlayer() == playerId) {
            return "start";
        } else {
            return "wait";
        }
    }
    
    public static String placeShips(int gameId, int playerId, int size, int startX, int startY, int endX, int endY) {
        GameList.get(gameId).placeShip(playerId, size, startX, startY, endX, endY);
        
        return "ok";
    }     
   
    /**
     * This method handles an attack by a player and its consequences
     * <p>
     * This method receives the game's identification and the attacking player.
     * It also receives the attacked position.
     * The method then calls the GameState method 
     * that processes the attacks.
     * If this method returns 6 critical hits, 
     * then the attacking player has won the game. 
     * The method saves this information
     * in the database, and sends end game message to the players.
     * 
     * @param gameId    game's identifier
     * @param playerId  attacking player's identifier
     * @param x         horizontal board position 
     * @param y         vertical board position
     * @return          message to inform player
     */
    public static String attack(int gameId, int playerId, int x, int y) {
        String message;
        int hits = 0;

        if (x != -1) {
            hits = GameList.get(gameId).attack(playerId, x, y);
            GameList.get(gameId).changeNextPlayer();
        }
        
        while ((GameList.get(gameId).getNextPlayer() != playerId) && GameList.containsKey(gameId)) {/*do nothing*/}
        
        if (!GameList.containsKey(gameId)) {
                return "end#lose";
        }
        
        if (GameList.get(gameId).getEndGame()) {
            GameList.remove(gameId);
            return "end#win";
        }
        
        int[] otherPlayerAttack;
        int[] thisPlayerAttack = GameList.get(gameId).getLastAtack(playerId);
        
        if (playerId == GameList.get(gameId).getPlayer1Id()) {
            otherPlayerAttack = GameList.get(gameId).getLastAtack(GameList.get(gameId).getPlayer2Id());
        } else {
            otherPlayerAttack = GameList.get(gameId).getLastAtack(GameList.get(gameId).getPlayer1Id());
        }
        
        switch (otherPlayerAttack[2]) {
                case 2:
                    message = "critical#";
                    break;
                case 1:
                    message = "hit#";
                    break;
                default:
                    message = "miss#";
                    break;
        }
        
        message += otherPlayerAttack[0] + "#" + otherPlayerAttack[1];
        
        if (x != -1) {
            switch (thisPlayerAttack[2]) {
                case 2:
                    message += "#critical";
                    break;
                case 1:
                    message += "#hit";
                    break;
                default:
                    message += "#miss";
                    break;
            }
        }
        
        if (hits == 6) {
            if (playerId == (GameList.get(gameId).getPlayer1Id())) {
                
                try {
                    DbGame.setGameWinner(playerId, gameId);
                } catch (SQLException e) {
                    System.out.println("Error saving ship" + e );
                }
            } else {
                
                try {
                    DbGame.setGameWinner(playerId, gameId);
                } catch(SQLException e) {
                    System.out.println("Error saving ship" + e );
                }
            }    
            
            GameList.remove(gameId);
            return "end#win";
        }
        
        return message;
    }
    
    
    public static String quitGame(int gameId, int playerId) {
        GameList.get(gameId).setEndGame();
        
        GameList.get(gameId).changeNextPlayer();
        
        return "ok";
    }
}
    

