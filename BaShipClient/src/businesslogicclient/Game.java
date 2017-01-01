package businesslogicclient;

import communicationclient.Protocol;

/**
 *
 * @author diogo
 */
public class Game {
    private static int id;
    private static String opponent;
    private static Board board1;
    private static Board board2;
    private static boolean placingShips;
    private static boolean myTurn;
    private static boolean firstTurn;
    private static boolean win;
    
    public static int getID(){
        return Game.id;
    }
    
    public static String getOpponent(){
        return Game.opponent;
    }
    
    public static boolean isWin(){
        return Game.win;
    }
    
    public static Board getBoard1(){
        return Game.board1;
    }
    
    public static boolean isMyTurn(){
        return Game.myTurn;
    }
    
    public static boolean isFirstTurn(){
        return Game.firstTurn;
    }
    
    public static boolean isPlacingShips(){
        return Game.placingShips;
    }
    
    public static void setID(int id){
        Game.id = id;
    }
    
    public static void setOpponent(String opponent){
        Game.opponent = opponent;
    }
    
    public static void setWin(boolean win){
        Game.win = win;
    }
    
    public static void setBoard(Board board){
        Game.board1 = board;
    }
    
    public static void setMyTurn(boolean myTurn){
        Game.myTurn = myTurn;
    }
    
    public static void setFirstTurn(boolean firstTurn){
        Game.firstTurn = firstTurn;
    }
    
    public static void setPlacingShips(boolean placingShips){
        Game.placingShips = placingShips;
    }
    
    public static boolean begin(){
        return Protocol.beginGame(Game.id, Authenticated.getID());
    }
    
    public static boolean isRunning(){
        if (Game.id == 0)
            return false;
        else
            return true;
    }
    
    public static boolean concede(){
        if(Protocol.concedeGame(Game.id, Authenticated.getID())){
            Game.reset();
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void reset(){
        Game.id = 0;
        Game.opponent = null;
        Game.board1 = null;
        Game.board2 = null;
        Game.myTurn = false;
        Game.firstTurn = false;
        Game.win = false;
    }
}
