package businesslogicclient;

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
    
    public static int getID(){
        return Game.id;
    }
    
    public static String getOpponent(){
        return Game.opponent;
    }
    
    public static String getOutcome(){
        return Game.outcome;
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
    
    public static void reset(){
        Game.id = 0;
        Game.opponent = null;
        Game.outcome = null;
        Game.board1 = null;
        Game.board2 = null;
        
    }
}
