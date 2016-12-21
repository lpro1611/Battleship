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
public class GameType {
    private int gameId;
    private int player1Id;
    private int player2Id;
    private int winnerId;
    
    public GameType(int gameId, int player1Id, int player2Id, int winnerId) {
        this.gameId = gameId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winnerId = winnerId;
    }
    
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    public void setPlayer1Id(int player1Id) {
        this.player1Id = player1Id;
    }
    
    public void setPlayer2Id(int player2Id) {
        this.player2Id = player2Id;
    }
    
    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }
    
    public int getGameId() {
        return gameId;
    }
    
    public int getPLayer1Id() {
        return player1Id;
    }
    
    public int getPLayer2Id() {
        return player2Id;
    }
    
    public int getWinnerId() {
        return winnerId;
    }
}
