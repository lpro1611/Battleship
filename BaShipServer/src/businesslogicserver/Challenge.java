package businesslogicserver;

/**
 *This handles the information of players involved
 *in setting up a challenge.
 * @author Diogo Dinis
 */
public class Challenge {
    private final int playerId1;
    private final int playerId2;
    private String state = "wait"; // wait, reject, accept
    
    /**
     * Constructor of Challenge class
     * 
     * @param playerId1 identication number of challenger
     * @param playerId2 identification umber of challengee
     */
    public Challenge(int playerId1, int playerId2) {
        this.playerId1 = playerId1;
        this.playerId2 = playerId2;
    }
    
    /**
     * This method changes the state of the challenge
     * 
     * @param state new state to be given the challenge
     */
    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * This method gets the currrent state of the challenge
     * 
     * @return  state of current challenge
     */
    public String getState() {
        return state;
    }
    
    /**
     * Gets the identification number of the player1.
     * 
     * @return player1's identifier
     */
    public int getPlayerId1() {
        return playerId1;
    }
    
     /**
     * Gets the identification number of the player2
     * 
     * @return player2's identifier
     */
    public int getPlayerId2() {
        return playerId2;
    }
}
