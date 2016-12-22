/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess.auxiliarstructs;

/**
 *  Struct that saves a game from the DB
 * 
 * @author diogo
 */
public class GameType {
    private int gameId;
    private int player1Id;
    private int player2Id;
    private int winnerId;
    
    /**
     * Set all GameType fields at beginning
     * 
     * @param gameId    Game's DB identifier
     * @param player1Id user's DB identifier of the player one
     * @param player2Id user's DB identifier of the player two
     * @param winnerId  user's DB identifer of the winner
     */
    public GameType(int gameId, int player1Id, int player2Id, int winnerId) {
        this.gameId = gameId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winnerId = winnerId;
    }
    
    /**
     * Set game's identifier
     * 
     * @param gameId Game's DB identifier
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    /**
     * Set player1's identifier for this game 
     * 
     * @param player1Id user's DB identifier of the player one
     */
    public void setPlayer1Id(int player1Id) {
        this.player1Id = player1Id;
    }
    
    /**
     * Set player2's identifier for this game 
     * 
     * @param player2Id user's DB identifier of the player two
     */
    public void setPlayer2Id(int player2Id) {
        this.player2Id = player2Id;
    }
    
    /**
     * Set winner's identifier for this game
     * 
     * @param winnerId user's DB identifer of the winner
     */
    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }
    
    /**
     * Get game's identifier
     * 
     * @return game's identifier
     */
    public int getGameId() {
        return gameId;
    }
    
    /**
     * Get player1's identifier
     * 
     * @return player1's identifier
     */
    public int getPLayer1Id() {
        return player1Id;
    }
    
    /**
     * Get player2's identifier
     * 
     * @return player2's identifier
     */
    public int getPLayer2Id() {
        return player2Id;
    }
    
    /**
     * Get winner's identifier
     * 
     * @return  winner's identifier
     */
    public int getWinnerId() {
        return winnerId;
    }
}
