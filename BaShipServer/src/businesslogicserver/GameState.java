package businesslogicserver;

import dataacess.DbGame;
import java.sql.SQLException;
import java.util.*;

/**
 * This class handles and saves the state of current game.
 * 
 * @author CPU TOSH
 *
 */
public class GameState {
    private final int gameId;
    private int dataBaseId;
    private final int player1Id;
    private final int player2Id;
    private int[][] board1 = new int[10][10];
    private int[][] board2 = new int[10][10];
    private List<Ship> player1Ships = new ArrayList<>(); 
    private List<Ship> player2Ships = new ArrayList<>();
    private boolean ready1;
    private boolean ready2;
    private int criticalHits1;
    private int criticalHits2;
    private int nextPlayer;
    private int[] lastPlayer1Attack = new int[3];
    private int[] lastPlayer2Attack = new int[3];
    private boolean end;
    private boolean treta;
    
    /**
     * This is the constructor for the GameState
     * <p>
     * It receives the identification numberds o the gam and players.
     * It places the in the correct fields. Afterward both boards are initialized
     * as empty, and all the five boats used in game are initialized.
     * 
     * @param gameId identification number of game
     * @param player1Id identification number of player 1
     * @param player2Id identification number of player 2
     */
    public GameState(int gameId, int player1Id, int player2Id) {
        this.gameId = gameId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        ready1 = false;
        ready2 = false;
        criticalHits1 = 0;
        criticalHits2 = 0;
        Random nextPlayerGenerator = new Random();
        nextPlayer = nextPlayerGenerator.nextInt(2) + 1;
        treta = true;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.board1[i][j] = 0;
                this.board2[i][j] = 0;
            }
        }
        
        lastPlayer1Attack[0] = -1;
        lastPlayer1Attack[1] = -1;
        lastPlayer1Attack[2] = -1;
        
        lastPlayer2Attack[0] = -1;
        lastPlayer2Attack[1] = -1;
        lastPlayer2Attack[2] = -1;
        
        end = false;
        dataBaseId = 0;
        
        player1Ships.add(new Ship(2)); //cria Destroyer,  os seis barcos aparecem ja ordenados na lista
        player1Ships.add(new Ship(3));
        player1Ships.add(new Ship(3));
        player1Ships.add(new Ship(4));
        player1Ships.add(new Ship(5));

        player2Ships.add(new Ship(2)); //cria Destroyer,  os seis barcos aparecem ja ordenados na lista
        player2Ships.add(new Ship(3));
        player2Ships.add(new Ship(3));
        player2Ships.add(new Ship(4));
        player2Ships.add(new Ship(5));   
    }
    
    
    /**
     * Set the identifier of game in the database
     * 
     * @param dataBaseId game's id in the data base
     */
    public void setDataBaseGameId(int dataBaseId) {
        this.dataBaseId = dataBaseId;
    }
    
    /**
     * Get he identifier of game in the datbase
     * 
     * @return  game's id in the data base 
     */
    public int getDataBaseGameId() {
        return dataBaseId;
    }
    
    /**
     *  This method is to indicate that the match end.
     */
    public void setEndGame() {
        end = true;
    }
    
    /**
     * Returns if the match is finish or not. 
     * 
     * @return end (true or false).
     */
    public boolean getEndGame() {
        return end;
    }
    
    /**
     * This method cn return a simple struct with last player attack.
     * 
     * @param playerId  player's identifier
     * @return          last player attack
     */
    public int[] getLastAtack(int playerId) {
        if (playerId == player1Id) {
            return lastPlayer1Attack;
        } else {
            return lastPlayer2Attack;
        }
    }
    
    /**
     * Get what player is the current turn.
     * 
     * @return player's identifier
     */
    public int getNextPlayer() {
        if (nextPlayer == 1) {
            return player1Id;
        } else {
            return player2Id;
        }
    }
    
    /**
     * Change next player turn.
     */
    public void changeNextPlayer() {
        if (nextPlayer == 1) {
            nextPlayer = 2;
        } else {
            nextPlayer = 1;
        }
    }
    
    /**
     * This method return the id number of player 1
     * 
     * @return player1's identifier
     */
    public int getPlayer1Id()  {
        return player1Id;
    }
    /**
     * This method return the id number of player 2
     * 
     * @return player2's identifier
     */
    public int getPlayer2Id()  {
        return player2Id;
    }
    
    /**
     * This method handles the ship placement
     * <p>
     * This method receives the identification number of the player 
     * and the boat. It also receives the starting and end position of the boat.
     * The method fills the correct positions of the player's board with 
     * the id of the boat. This signals that the boat is in those positions
     * 
     * @param playerId  identification number of player
     * @param size      ship's size
     * @param startX    starting horizontal position
     * @param startY    starting vertical position
     * @param endX      final horizontal position
     * @param endY      final vertical position 
     */
    public void placeShip(int playerId, int size, int startX, int startY, int endX, int endY) {
        //no caso de string id é String e eu depois passo a numero
        int placey = startY, placex = startX;
        int boatid;
        
        if (endX < startX) { //certifica ordem crescente 
            placex = endX;
        }
        
        if (endY < startY) {
            placey = endY;
        }
        
         if (size == 5) {
                boatid = 5;
            } else if (size == 4) {
                boatid = 4;
            } else if (size == 2) {
                boatid = 1;
            } else if (treta) {
                boatid = 2;
                treta = false;
            } else {
                boatid = 3;
            }
        
        if (player1Id == playerId) {
            for (int i = 0; i < size; i++) {
                if (startY == endY) {
                    board1[placex + i][placey] = boatid;
                }

                if (startX == endX) {
                    board1[placex][placey + i] = boatid;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (startY == endY) {
                    board2[placex + i][placey] = boatid;
                }

                if (startX == endX) {
                    board2[placex][placey + i] = boatid;
                }
            }
        }
      
        try {
            DbGame.setShipPosition(boatid,startX,startY,endX,endY, player1Id, getDataBaseGameId());
        } catch (SQLException e) {
            System.out.println("Error savin ship" + e );
        }
        //.setShipPosition(id,startx,starty,endx,endy, 1, gameId );
    }
    
    /**
     * This method changes the GameState based on an attack
     * <p>
     * 
     * This method receives the identification number of the attacker.
     * It also receives the attacking positions. It check the attacking postion
     * on the enemy board. If there is a boat in that position it calls the hit method
     * of the Ship class. If that method returns a number of hits, that equals the size o the ship,
     * then the ship has sunk. In that case the amount of critical hits is incremented.
     * Finally a message is sent to the players and the method returns
     * the amount of critical hits.
     * 
     * @param player identification number of player 
     * @param x horizontal position
     * @param y vertical position
     * @return  number of ships the player has sunk
     */
    public int attack(int player, int x, int y) {
        int[] hit = {0,0};
        boolean saveHit = false;
        
        if (player1Id == player) {
            hit[0] = board2[x][y];
            
            if (hit[0] != 0) {
                hit[1] = (player2Ships.get(hit[0] - 1)).hit(); //atualiza barcos
            }
            
            criticalHits1 = criticalHits1 + hit[1];
            
            lastPlayer1Attack[0] = x;
            lastPlayer1Attack[1] = y;
            
            if (hit[0] == 0) {
                lastPlayer1Attack[2] = 0;
            } else if (hit[1] == 0) {
                lastPlayer1Attack[2] = 1;
            } else {
                lastPlayer1Attack[2] = 2;
            }
            
            try {
                DbGame.setMovePosition(x,y,saveHit,player, getDataBaseGameId());
            } catch(SQLException e) {
                System.out.println("Error savin ship" + e );
            }
            
            return criticalHits1;   
        } else {
            hit[0] = board1[x][y];
            
            if (hit[0] != 0) {
                hit[1] = (player1Ships.get(hit[0] - 1)).hit();//atualiza barcos
                saveHit = true;
            }
            
            criticalHits2 += hit[1];
            
            lastPlayer2Attack[0] = x;
            lastPlayer2Attack[1] = y;
            
            if (hit[0] == 0) {
                lastPlayer2Attack[2] = 0;
            } else if (hit[1] == 0) {
                lastPlayer2Attack[2] = 1;
            } else {
                lastPlayer2Attack[2] = 2;
            }
            
            try {
                DbGame.setMovePosition(x,y,saveHit,player, getDataBaseGameId());
            } catch(SQLException e) {
                System.out.println("Error savin ship" + e );
            }
            
            return criticalHits2;
        } 
    }
    
    /**
     * This method signal that a player is ready to start the game
     * If both players are ready a begin game message is sent to both. 
     * 
     * @param player    identification number of player 
     * @return          String message to the cliente
     */
    public String playerReady(int player) { 
        if (player1Id == player) {
            ready1 = true;
        } else {
            ready2 = true;
        }
        
        if (ready1 && ready2) {
            //Protocol.beginGame(Player2)
            return "start";
        } else {
            return "wait";
        }
    }
    /**
     * This method gets the value on the players board.
     * 
     * @param x         horizontal position
     * @param y         vertical position
     * @param player    identification number of the player
     * @return          value on the players board
     */
    public int getPosition(int x, int y, int player){
        if(player == player1Id)
        return board1 [x][y];
        else
        return board2 [x] [y];    
    }
    
    /**
     * This method checks the ready state of a player
     * 
     * @param player    identification number of the player
     * @return          the ready state of the player
     */
    public boolean getPlayerState(int player){
        if(player == player1Id)
        return ready1;
        else
        return ready2;    
    }
}
