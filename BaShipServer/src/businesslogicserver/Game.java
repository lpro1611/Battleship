/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;
import java.util.*;
import javafx.util.*;
import java.lang.*;


/**
 *
 * @author CPU TOSH
 */
public  class Game {
    public static Map < Integer, GameState> GameList;
    public static int id;
    
        public Game(){
            //nada esta sempre a correr
            id=0;
            }
        
        public void createGame(String Player1, String Player2){
            (this.GameList).put(id,new GameState(id,Player1, Player2));
            id++; //ainda tenho de ver melhor maneira de buscar id
        }
         
         
         public String attack(int GameID, String player, int x, int y){
             int hits=(this.GameList).get(GameID).attack(player, x, y);
             if(hits == 6){
                 if(player ==((this.GameList).get(GameID).Player1)){
                     //Protocol.endGame(..) Para ambos jogaores Player 1 victorious
                    (this.GameList).remove(GameID);
                    id--;
                  }
                 else{
                      //Protocol.endGame(..) Para ambos jogaoresPlayer 2 victorious
                    (this.GameList).remove(GameID);
                    id--;
                 }      
               }
             return "ok";//se necessario
        }
}
    

