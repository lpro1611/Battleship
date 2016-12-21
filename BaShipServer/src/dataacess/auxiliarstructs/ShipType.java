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
    
    public ShipType(int idOnGame, int beginX, int beginY, int endX, int endY, int playerId, int gameId) {
        this.idOnGame = idOnGame;
        this.beginX = beginX;
        this.beginY = beginY;
        this.endX = endX;
        this.endY = endY;
        this.playerId = playerId;
        this.gameId = gameId;
    }
    
    public void setIdOnGame(int idOnGame) {
        this.idOnGame = idOnGame;
    }
    
    public void setBeginX(int beginX) {
        this.beginX = beginX;
    }
    
    public void setBeginY(int beginY) {
        this.beginY = beginY;
    }
    
    public void setEndX(int endX) {
        this.endX = endX;
    }
    
    public void setEndY(int endY) {
        this.endY = endY;
    }
    
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    public int getIdOnGame() {
        return idOnGame;
    }
    
    public int getBeginX() {
        return beginX;
    }
    
    public int getBeginY() {
        return beginY;
    }
    
    public int getEndX() {
        return endX;
    }
    
    public int getEndY() {
        return endY;
    }
    
    public int getPLayerId() {
        return playerId;
    }
    
    public int getGameId() {
        return gameId;
    }
}
