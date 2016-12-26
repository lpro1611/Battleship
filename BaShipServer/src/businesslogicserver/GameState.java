/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

import dataacess.DbGame;
import java.sql.SQLException;
import java.util.*;
/**
 * Eu sei que falta chamar DB
 * @author CPU TOSH
 *
 */
public class GameState {
    private final int gameId;
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

    public GameState(int gameId, int player1Id, int player2Id) {
        this.gameId = gameId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.ready1 = false;
        this.ready2 = false;
        this.criticalHits1 = 0;
        this.criticalHits2 = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.board1[i][j] = 0;
                this.board2[i][j] = 0;
            }
        }

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
    
    public int getPlayer1Id()  {
        return player1Id;
    }
    
    public int getPlayer2Id()  {
        return player2Id;
    }
    
    public void placeShip(int playerId, int id, int startX, int startY, int endX, int endY) {
        //no caso de string id é String e eu depois passo a numero
        int placey = startY, placex = startX;
        int size;
        
        if (endX < startX) { //certifica ordem crescente 
            placex = endX;
        }
        
        if (endY < startY) {
            placey = endY;
        }
        
        if (player1Id == playerId) {
            size = (player1Ships.get(id-1)).size;
            
            for (int i = 0; i < size; i++) {
                if (startY == endY) {
                    board1[placex + i][placey] = id;
                }

                if (startX == endX) {
                    board1[placex][placey + i] = id;
                }
            }
        } else {
            size = (player2Ships.get(id-1)).size;
            
            for (int i = 0; i < size; i++) {
                if (startY == endY) {
                    board2[placex + i][placey] = id;
                }

                if (startX == endX) {
                    board2[placex][placey + i] = id;
                }
            }
        }
      
        try {
            DbGame.setShipPosition(id,startX,startY,endX,endY, player1Id, gameId );
        } catch (SQLException e) {
            System.out.println("Error savin ship" + e );
        }
        //.setShipPosition(id,startx,starty,endx,endy, 1, gameId );
    }
     
    public int attack(int player, int x, int y) {
        int[] hit = {0,0};
        boolean saveHit =false;
        
        if (player1Id == player) {
            hit[0] = board2[x][y];
            
            if (hit[0] != 0) {
                hit[1] = (player2Ships.get(hit[0] - 1)).hit(); //atualiza barcos
           
            }
            
            criticalHits1 = criticalHits1 + hit[1];
            //Protocol.hit(Player2, int x, int y, hit, "My Board"); mensagem para o outro jogado4
            //Protocol.hit(Player1, int x, int y, hit, "Enemy Board");
            
            try {
                DbGame.setMovePosition(x,y,saveHit,player,gameId );
            } catch(SQLException e) {
                System.out.println("Error savin ship" + e );
            }
            
            return criticalHits1;   //retorno para o Protocolo se necessario
        } else {
            hit[0] = board1[x][y];
            
            if (hit[0] != 0) {
                hit[1] = (player1Ships.get(hit[0] - 1)).hit();//atualiza barcos
                saveHit = true;
            }
            
            //Protocol.hit(Player1, int x, int y, hit, "MyBoard"); mensagem para o outro jogado
            //Protocol.hit(Player2, int x, int y, hit, "Enemy Board");
            criticalHits2 += hit[1];
            
            try {
                DbGame.setMovePosition(x,y,saveHit,player,gameId );
            } catch(SQLException e) {
                System.out.println("Error savin ship" + e );
            }
            
            return criticalHits2;
        } 
    }
     
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
}
