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
     * @param x         X coordinate
     * @param y         Y coordinate
     * @param hit       hit or miss a ship
     * @param playerId  user's DB identifier of the player
     * @param gameId    game's DB identifier
     */
    public MoveType(int x, int y, boolean hit, int playerId, int gameId) {
        this.x = x;
        this.y = y;
        this.hit = hit;
        this.playerId = playerId;
        this.gameId = gameId;
    }
    
    /**
     * Set X coordinate of this moviment
     * 
     * @param x X coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Set Y coordinate of this moviment
     * 
     * @param y Y coordinate
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
     * @param gameId game's DB identifier
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    /**
     * Set if this move was a hit or was a shot in the water
     * 
     * @param hit hit or miss a ship
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }  
    
    /**
     * Get Y coordinate of this moviment
     * 
     * @return Y coordinate 
     */
    public int getX() {
        return x;
    }

    /**
     * Get Y coordinate of this moviment
     * 
     * @return Y coordinate 
     */
    public int getY() {
        return y;
    }
    
    /**
     * Get player's identifier
     * 
     * @return player's identifier
     */
    public int getPlayerId() {
        return playerId;
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
     * Get type of shot of this move, hit or miss
     * 
     * @return hit or miss
     */
    public boolean getHit() {
        return hit;
    }
}
