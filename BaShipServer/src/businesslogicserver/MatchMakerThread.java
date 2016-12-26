/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;
import java.util.*;

/**
 *
 * @author CPU TOSH
 */
public class MatchMakerThread extends Thread{
    
    public List<Integer> Players = new ArrayList<>();
    public MatchMakerThread(String name){
    }

    public void run(){
            while(true){
                if(Players.size()>1){
                    Game.createGame(Players.get(0),Players.get(1));
                    Players.remove(1);
                    Players.remove(0);
                }
                else{
                    try {
                        Thread.sleep(500);//Não gastar muito tempo
                        }
                    catch (InterruptedException e) {
                            //System.out.println("My " + name + " is interrupted.");
                        }
                    }
            }
    }
}
