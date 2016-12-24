package businesslogicclient;

import communicationclient.Protocol;

/**
 *
 * @author diogo
 */
public class Game {
    private static int id;
    private static String opponent;
    private static String outcome;
    private static Board board1;
    private static Board board2;
    private static boolean myTurn;
    
    public static int getID(){
        return Game.id;
    }
    
    public static String getOpponent(){
        return Game.opponent;
    }
    
    public static String getOutcome(){
        return Game.outcome;
    }
    
    public static Board getBoard1(){
        return Game.board1;
    }
    
    public static boolean isMyTurn(){
        return Game.myTurn;
    }
    
    public static void setID(int id){
        Game.id = id;
    }
    
    public static void setOpponent(String opponent){
        Game.opponent = opponent;
    }
    
    public static void setOutcome(String outcome){
        Game.outcome = outcome;
    }
    
    public static void setBoard(Board board){
        Game.board1 = board;
    }
    
    public static void setMyTurn(boolean myTurn){
        Game.myTurn = myTurn;
    }
    
    public static boolean begin(){
        return Protocol.beginGame(Game.id, Authenticated.getID());
    }
    
    public static void reset(){
        Game.id = 0;
        Game.opponent = null;
        Game.outcome = null;
        Game.board1 = null;
        Game.board2 = null;
        
    }
}
