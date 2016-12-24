package businesslogicserver;

/**
 *
 * @author Diogo Dinis
 */
public class Challenge {
    private final int playerId1;
    private final int playerId2;
    private String state = "wait"; // wait, reject, accept
    
    public Challenge(int playerId1, int playerId2) {
        this.playerId1 = playerId1;
        this.playerId2 = playerId2;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
    
    public int getPlayerId1() {
        return playerId1;
    }
    
    public int getPlayerId2() {
        return playerId2;
    }
}
