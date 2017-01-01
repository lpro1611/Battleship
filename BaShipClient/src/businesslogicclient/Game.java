package businesslogicclient;

import communicationclient.Protocol;

/**
 * Represents the game.
 *
 * @author Diogo Recharte
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
    
    /**
     * Class Constructor
     */
    public Game(){}
    
    /**
     * Returns the game id number.
     * <p>
     * This method always returns successfully.
     * 
     * @return  game identification number.
     */
    public static int getID(){
        return Game.id;
    }
    /**
     * Returns the opponent's name.
     * <p>
     * This method always returns successfully.
     * 
     * @return  opponent's name.
     */
    public static String getOpponent(){
        return Game.opponent;
    }
    
    /**
     * Returns if the game was a win or a loss.
     * <p>
     * This method always returns successfully.
     * 
     * @return      <code>true</code> if won; 
     *              <code>false</code> otherwise.
     */
    public static boolean isWin(){
        return Game.win;
    }
    
    /**
     * Returns board number one
     * <p>
     * This method always returns successfully.
     * 
     * @return  board number one.
     */
    public static Board getBoard1(){
        return Game.board1;
    }
    
    /**
     * Returns if it's my turn or not.
     * <p>
     * This method always returns successfully.
     * 
     * @return      <code>true</code> if it's my turn; 
     *              <code>false</code> otherwise.
     */
    public static boolean isMyTurn(){
        return Game.myTurn;
    }
    
    /**
     * Returns if it's the first turn or not.
     * <p>
     * This method always returns successfully.
     * 
     * @return      <code>true</code> if it's the first turn; 
     *              <code>false</code> otherwise.
     */
    public static boolean isFirstTurn(){
        return Game.firstTurn;
    }
    
    /**
     * Returns if the user is placing the ships or not.
     * <p>
     * This method always returns successfully.
     * 
     * @return      <code>true</code> if it's placing ships; 
     *              <code>false</code> otherwise.
     */
    public static boolean isPlacingShips(){
        return Game.placingShips;
    }
    
    /**
     * Sets the game id number.
     *
     * @param id    game identification number
     */
    public static void setID(int id){
        Game.id = id;
    }
    
    /**
     * Sets the opponent user name.
     * 
     * @param opponent      opponent user name
     */
    public static void setOpponent(String opponent){
        Game.opponent = opponent;
    }
    
    /**
     * Sets the outcome as a win or not.
     * 
     * @param win   <code>true</code> if it was a win; 
     *              <code>false</code> otherwise.
     */
    public static void setWin(boolean win){
        Game.win = win;
    }
    
    /**
     * Sets the board.
     * 
     * @param board     board
     */
    public static void setBoard(Board board){
        Game.board1 = board;
    }
    
    /**
     * Sets if it's my turn or not.
     * 
     * @param myTurn    <code>true</code> if it's my turn; 
     *                  <code>false</code> otherwise.
     */
    public static void setMyTurn(boolean myTurn){
        Game.myTurn = myTurn;
    }
    
    /**
     * Sets if it's the first turn or not.
     * 
     * @param firstTurn     <code>true</code> if it's the first turn; 
     *                      <code>false</code> otherwise.
     */
    public static void setFirstTurn(boolean firstTurn){
        Game.firstTurn = firstTurn;
    }
    
    /**
     * Sets if the user is placing the ships or not.
     * 
     * @param placingShips      <code>true</code> if the user is placing the ships; 
     *                          <code>false</code> otherwise.
     */
    public static void setPlacingShips(boolean placingShips){
        Game.placingShips = placingShips;
    }
    
    /**
     * Starts the game.
     * <p>
     * Calls the communication protocol to tell the server that
     * the user is ready to start the game.
     *
     * @return      <code>true</code> if successful; 
     *              <code>false</code> otherwise.
     */
    public static boolean begin(){
        return Protocol.beginGame(Game.id, Authenticated.getID());
    }
    
    /**
     * Returns if the game is running or not.
     * 
     * @return      <code>true</code> if it's running; 
     *              <code>false</code> otherwise.
     */
    public static boolean isRunning(){
        if (Game.id == 0)
            return false;
        else
            return true;
    }
    
    /**
     * Quits the game, counting as a loss.
     * <p>
     * Calls the communication protocol to tell the server that
     * the user is quitting the game.
     * 
     * @return      <code>true</code> if successful; 
     *              <code>false</code> otherwise.
     */
    public static boolean concede(){
        if(Protocol.concedeGame(Game.id, Authenticated.getID())){
            Game.reset();
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Resets the game data.
     */
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
