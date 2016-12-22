/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess.auxiliarstructs;

/**
 * Struct that saves a move from the DB
 * 
 * @author diogo
 */
public class MoveType {
    private int x;
    private int y;
    private int playerId;
    private int gameId;
    private boolean hit;
    
    /**
     * Set all MoveType fields at beginning
     * 
     * @param x
     * @param y
     * @param hit
     * @param playerId  user's DB identifier of the player
     * @param gameId    Game's DB identifier
     */
    public MoveType(int x, int y, boolean hit, int playerId, int gameId) {
        this.x = x;
        this.y = y;
        this.hit = hit;
        this.playerId = playerId;
        this.gameId = gameId;
    }
    
    /**
     *
     * 
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * 
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Set player's identifier for this game
     * 
     * @param playerId user's DB identifier of the player
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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
     * 
     * @param hit 
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }  
    
    /**
     * 
     * @return 
     */
    public int getX() {
        return x;
    }
    
    /**
     * 
     * @return 
     */
    public int getY() {
        return y;
    }
    
    /**
     * 
     * @return 
     */
    public int getPLayerId() {
        return playerId;
    }
    
    /**
     * 
     * @return 
     */
    public int getGameId() {
        return gameId;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getHit() {
        return hit;
    }
}
