package businesslogicserver;

import java.util.*;

/**
 * This cllass handles the random matchmaking of the game.
 * 
 * @author CPU TOSH
 */
public class MatchMakerThread extends Thread{
    public List<Integer> Players = new ArrayList<>();
    
    public MatchMakerThread(String name) {}

    /**
     * run method of the Thread.
     */
    @Override
    public void run(){
        while (true) {
            if (Players.size() > 1) {
                Game.createGame(Players.get(0), Players.get(1));
                Players.remove(1);
                Players.remove(0);
            } else {
                try {
                    Thread.sleep(500); //Não gastar muito tempo
                } catch (InterruptedException e) {
                    //System.out.println("My " + name + " is interrupted.");
                }
            }
        }
    }
}
