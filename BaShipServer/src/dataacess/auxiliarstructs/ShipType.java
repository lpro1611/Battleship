/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess.auxiliarstructs;

/**
 *
 * @author diogo
 */
public class ShipType {
    private int idOnGame;
    private int beginX;
    private int beginY;
    private int endX;
    private int endY;
    private int playerId;
    private int gameId;
    
    
    /**
     * Set all ShipType fields at beginning
     * 
     * @param idOnGame  ship's identifier
     * @param beginX    X coordinate of ship's beginnig
     * @param beginY    Y coordinate of ship's beginnig
     * @param endX      X coordinate of ship's ending
     * @param endY      Y coordinate of ship's ending
     * @param playerId  user's DB identifier of the player
     * @param gameId    game's DB identifier
     */
    public ShipType(int idOnGame, int beginX, int beginY, int endX, int endY, int playerId, int gameId) {
        this.idOnGame = idOnGame;
        this.beginX = beginX;
        this.beginY = beginY;
        this.endX = endX;
        this.endY = endY;
        this.playerId = playerId;
        this.gameId = gameId;
    }
    
    /**
     * Set ship's identifier in the game
     * 
     * @param idOnGame ship's identifier
     */
    public void setIdOnGame(int idOnGame) {
        this.idOnGame = idOnGame;
    }
    
    /**
     * Set X coordinate of the bow's ship
     * 
     * @param beginX X coordinate of ship's beginnig
     */
    public void setBeginX(int beginX) {
        this.beginX = beginX;
    }
    
    /**
     * Set Y coordinate of the bow's ship
     * 
     * @param beginY X coordinate of ship's beginnig
     */
    public void setBeginY(int beginY) {
        this.beginY = beginY;
    }
    
    /**
     * Set X coordinate of the stern's ship
     * 
     * @param endX X coordinate of ship's ending
     */    
    public void setEndX(int endX) {
        this.endX = endX;
    }
    
    /**
     * Set Y coordinate of the stern's ship
     * 
     * @param endY Y coordinate of ship's ending
     */    
    public void setEndY(int endY) {
        this.endY = endY;
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
     * Get ship's identifier in the game
     * 
     * @return ship's identifier
     */
    public int getIdOnGame() {
        return idOnGame;
    }
    
    /**
     * Get X coordinate of the bow's ship
     * 
     * @return X coordinate of ship's beginnig 
     */
    public int getBeginX() {
        return beginX;
    }
    
    /**
     * Get Y coordinate of the bow's ship
     * 
     * @return Y coordinate of ship's beginnig 
     */
    public int getBeginY() {
        return beginY;
    }
    
    /**
     * Get X coordinate of the stern's ship
     * 
     * @return X coordinate of ship's ending 
     */
    public int getEndX() {
        return endX;
    }
    
    /**
     * Get Y coordinate of the stern's ship
     * 
     * @return Y coordinate of ship's ending 
     */
    public int getEndY() {
        return endY;
    }
    
    /**
     * Get player's identifier
     * 
     * @return player's identifier
     */
    public int getPLayerId() {
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
}
