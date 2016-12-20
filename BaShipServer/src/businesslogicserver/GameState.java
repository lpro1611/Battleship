/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

import java.util.*;
/**
 * Eu sei que falta chamar DB
 * @author CPU TOSH
 *
 */
public class GameState {
    int gameId;
    String Player1;
    String Player2;
    int[][] Board1;
    int[][] Board2;
    List<Ship> Player1Ships; 
    List<Ship> Player2Ships;
    boolean ready1;
    boolean ready2;
    int criticalHits1;
    int criticalHits2;

    public GameState(int gameId, String Player1, String Player2) {
        int i, j;
        this.gameId = gameId;
        this.Player1 = Player1;
        this.Player2 = Player2;
        this.ready1 = false;
        this.ready2 = false;
        this.criticalHits1 = 0;
        this.criticalHits2 = 0;

        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                this.Board1[i][j] = 0;
                this.Board2[i][j] = 0;
            }
        }

        this.Player1Ships.add(new Ship(2)); //cria Destroyer os seis barcos aparcem ja ordenados na lista
        this.Player1Ships.add(new Ship(3));
        this.Player1Ships.add(new Ship(3));
        this.Player1Ships.add(new Ship(4));
        this.Player1Ships.add(new Ship(5));

        this.Player2Ships.add(new Ship(2)); //cria Destroyer os seis barcos aparcem ja ordenados na lista
        this.Player2Ships.add(new Ship(3));
        this.Player2Ships.add(new Ship(3));
        this.Player2Ships.add(new Ship(4));
        this.Player2Ships.add(new Ship(5));   
    }
       
    public void placeShip(String player, int id, int startx, int starty, int endx, int endy) {
        //no caso de string id é String e eu depois passo a numero
        int placey = starty, placex = startx;
        int size;
        
        if (Player1.equals(player)) {
            size = (Player1Ships.get(id)).size;
        } else {
            size = (Player2Ships.get(id)).size;
        }
        
        if (endx < startx) { //certifica ordem crescente 
            placex = endx;
        }
        
        if (endy < starty) {
            placey = endy;
        }
             
        for (int i = 0; i < size; i++) {
            if (starty == endy) {
                Board1[placex + i][placey] = id;
            }

            if (startx == endx) {
                Board1[placex][placey + i] = id;
            }
        }
    }
     
    public int attack(String player, int x, int y) {
        int[] hit = {0,0};
        
        if (Player1.equals(player)) {
            hit[0] = Board2[x][y];
            
            if (hit[0] != 0) {
                hit[1] = (Player2Ships.get(hit[0] - 1)).hit(); //atualiza barcos
            }
            
            criticalHits1 += hit[1];
            //Protocol.hit(Player2, int x, int y, hit, "My Board"); mensagem para o outro jogado4
            //Protocol.hit(Player1, int x, int y, hit, "Enemy Board");
            
            return criticalHits1;   //retorno para o Protocolo se necessario
        } else {
            hit[0] = Board1[x][y];
            
            if (hit[0] != 0) {
                hit[1] = (Player1Ships.get(hit[0] - 1)).hit();//atualiza barcos
            }
            
            //Protocol.hit(Player1, int x, int y, hit, "MyBoard"); mensagem para o outro jogado
            //Protocol.hit(Player2, int x, int y, hit, "Enemy Board");
            criticalHits2 += hit[1];
            
            return criticalHits2;
        } 
    }
     
    public String PlayerReady(String player) { //Eu sei que o nome do metodo não esta de acordo a regras
        if (Player1.equals(player)) {
            ready1 = true;
        } else {
            ready2 = true;
        }
        
        if (ready1 && ready2) {
            //Protocol.beginGame(Player2)
            return "Begin";
        } else {
            return "Wait for other Player";
        }
    }
}
