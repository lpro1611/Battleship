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
public class MoveType {
    private int x;
    private int y;
    private int playerId;
    private int gameId;
    private boolean hit;
    
    public MoveType(int x, int y, boolean hit, int playerId, int gameId) {
        this.x = x;
        this.y = y;
        this.hit = hit;
        this.playerId = playerId;
        this.gameId = gameId;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    public void setHit(boolean hit) {
        this.hit = hit;
    }  
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getPLayerId() {
        return playerId;
    }
    
    public int getGameId() {
        return gameId;
    }
    
    public boolean getHit() {
        return hit;
    }
}
