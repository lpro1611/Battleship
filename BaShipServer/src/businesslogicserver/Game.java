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
    public static Map <Integer, GameState> GameList;
    public static int id;
    
    public Game() {
        //nada esta sempre a correr
        id = 0;
    }

    public void createGame(int Player1, int Player2) {
        GameList.put(id, new GameState(id,Player1, Player2));
        try{
            DbGame.createGame(Player1, Player2);
            }
            catch(SQLException e){
            System.out.println("Error savin ship" + e );
            }
        id++; //ainda tenho de ver melhor maneira de buscar id
    }

    public String attack(int GameID, int player, int x, int y) {
        int hits = GameList.get(GameID).attack(player, x, y);
        if (hits == 6) {
            if (player == (GameList.get(GameID).Player1)) {
                //Protocol.endGame(..) Para ambos jogaores Player 1 victorious
                 try{
                    DbGame.setGameWinner(player, GameID);
                    }
                catch(SQLException e){
                System.out.println("Error savin ship" + e );
                }
                GameList.remove(GameID);
                id--;
            }
            else {
                //Protocol.endGame(..) Para ambos jogaoresPlayer 2 victorious
                 try{
                     DbGame.setGameWinner(player, GameID);
                    }
                 catch(SQLException e){
                    System.out.println("Error savin ship" + e );
                    }
                GameList.remove(GameID);
                id--;
            }      
        }
        return "ok"; //se necessario
    }
}
    

