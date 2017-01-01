package businesslogicserver;

import dataacess.DbGame;
import java.sql.SQLException;
import java.util.*;


/**
 * Hold the information from all the games currently played.
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
        int dataBaseId = 0;
        
        do {
            id = idGenerator.nextInt();
        } while (GameList.containsKey(id));
        
        GameList.put(id, new GameState(id, player1Id, player2Id));
        
        try {
            dataBaseId = DbGame.createGame(player1Id, player2Id);
        } catch (SQLException e) {
            System.out.println("Error saving ship (Isto não é um barco!)" + e);
        }
        
        GameList.get(id).setDataBaseGameId(dataBaseId);
        
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
    
    /**
     * This method handles the placement of ships.
     * <p>
     * It receive the ship's parameteres and connect to the next layer to 
     * save the ship in this match. 
     * 
     * @param gameId    game's identifier
     * @param playerId  player's identifier who put this ship
     * @param size      ship's size
     * @param startX    ship's begin x coordinate
     * @param startY    ship's begin y coordinate
     * @param endX      ship's finish x coordinate
     * @param endY      ship's finish y coordinate
     * @return          message to the previous layer
     */
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
        int hits;
        int[] playerAttack;

        if (GameList.get(gameId).getEndGame()) {
            GameList.remove(gameId);
            return "end#win";
        }   
        
        hits = GameList.get(gameId).attack(playerId, x, y);
        
        playerAttack = GameList.get(gameId).getLastAtack(playerId);
        
        switch (playerAttack[2]) {
            case 2:
                message = "critical";
                break;
            case 1:
                message = "hit";
                break;
            default:
                message = "miss";
                break;
        }
        
        System.out.println(hits);
        
        if (hits == 5) {
            if (playerId == (GameList.get(gameId).getPlayer1Id())) {
                
                System.out.println("player 1 wins");
                
                try {
                    DbGame.setGameWinner(playerId, GameList.get(gameId).getDataBaseGameId());
                } catch (SQLException e) {
                    System.out.println("Error saving ship" + e );
                }
            } else {
                 System.out.println("player 2 wins");
                try {
                    DbGame.setGameWinner(playerId, GameList.get(gameId).getDataBaseGameId());
                } catch(SQLException e) {
                    System.out.println("Error saving ship" + e );
                }
            }    
            
            GameList.remove(gameId);
            message = "end#win";
        }
        
        GameList.get(gameId).changeNextPlayer();
        
        return message;
    }
    
    public static String oponentAttack(int gameId, int playerId) {
        String message;
        int[] otherPlayerAttack;
        
        try {
            while((GameList.get(gameId).getNextPlayer() != playerId) && GameList.containsKey(gameId)) {/*do nothing*/}
        } catch (Exception e) {
            if (!GameList.containsKey(gameId)) {
                return "end#lose";
            } else {
                return "error";
            }
        }
        
        
        
        if (GameList.get(gameId).getEndGame()) {
            GameList.remove(gameId);
            return "end#win";
        }   
        
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
        
        return message;
    }
    
    /**
     * End a game specific game based on a customer's request.
     * 
     * @param gameId    game's identifier
     * @param playerId  player's identifier who request the end of game
     * @return          messge to previous layer
     */
    public static String quitGame(int gameId, int playerId) {
        GameList.get(gameId).setEndGame();
        
        GameList.get(gameId).changeNextPlayer();
        
        return "ok";
    }
}