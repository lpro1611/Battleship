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
 *
 * @author CPU TOSH
 */
public class Game {
    public static Map <Integer, GameState> GameList = new HashMap<>();
    private static int id = 0;
    
    public Game() {}

    public static int createGame(int player1Id, int player2Id) {
        GameList.put(id, new GameState(id, player1Id, player2Id));
        
        try{
            DbGame.createGame(player1Id, player2Id);
        } catch(SQLException e) {
            System.out.println("Error saving ship" + e);
        }
        
        id++; //ainda tenho de ver melhor maneira de buscar id
        
        return id - 1;
    }

    public static String playerReady(int gameId, int playerId) {
        while (GameList.get(gameId).playerReady(playerId).equals("wait")) {
            /*do nothing*/
        }
        
        return "start";
    }
    
    public static String placeShips(int gameId, int playerId, int id, int startX, int startY, int endX, int endY) {
        GameList.get(gameId).placeShip(playerId, id, startX, startY, endX, endY);
        
        return "ok";
    }
    
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
                id--;
            } else {
                //Protocol.endGame(..) Para ambos jogaoresPlayer 2 victorious
                try {
                    DbGame.setGameWinner(playerId, gameId);
                } catch(SQLException e) {
                    System.out.println("Error saving ship" + e );
                }
                
                GameList.remove(gameId);
                id--;
            }      
        }
        return "ok";
    }
}
    

