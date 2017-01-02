package businesslogicserver;

import communicationserver.SendMessageSocket;
import java.util.*;

/**
 * This cllass handles the random matchmaking of the game.
 * 
 * @author CPU TOSH
 */
public class MatchMakerThread extends Thread{
    public List<Integer> players = new ArrayList<>();
    private int gameId;
    
    public MatchMakerThread(String name) {
        super(name);
    }

    /**
     * run method of the Thread.
     */
    @Override
    public void run(){
        
        while (true) {
            if (players.size() > 1) {
                gameId = Game.createGame(players.get(0), players.get(1));
                String message1 = "game#create# " + gameId + "#" + AuthenticatedUsers.authenticatedList.get(players.get(0)).getName();
                new SendMessageSocket(message1 , AuthenticatedUsers.authenticatedList.get(players.get(1)).getSocket()).start();
                String message2 = "game#create# " + gameId + "#" + AuthenticatedUsers.authenticatedList.get(players.get(1)).getName();
                new SendMessageSocket(message2 , AuthenticatedUsers.authenticatedList.get(players.get(2)).getSocket()).start();
                players.remove(1);
                players.remove(0);
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
