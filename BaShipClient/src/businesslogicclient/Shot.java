package businesslogicclient;

import communicationclient.Protocol;

/**
 * Represents an attack
 * 
 * @author Diogo Recharte
 */
public class Shot {
    private static int row;
    private static int col;
    private static boolean marked = false;
    private static boolean hit = false;
    private static boolean criticalHit = false;
    private static boolean finalHit = false;
    
    /**
     * Class constructor.
     */
    public Shot(){}
    
    /**
     * Sets the attack position.
     * 
     * @param row       attack row
     * @param col       attack column
     */
    public static void mark(int row, int col){
        Shot.row = row;
        Shot.col = col;
        Shot.marked = true;
    }
    
    /**
     * Sets whether the attack was a hit or not.
     * 
     * @param hit       <code>true</code> if it's a hit; 
     *                  <code>false</code> otherwise.
     */
    public static void setHit(boolean hit){
        Shot.hit = hit;
    }
    
    /**
     * Sets whether the attack was a critical hit or not.
     * 
     * @param criticalHit       <code>true</code> if it's a critical hit; 
     *                          <code>false</code> otherwise.
     */
    public static void setCriticalHit(boolean criticalHit){
        Shot.criticalHit = criticalHit;
    }
    
    /**
     * Sets whether the attack was the final hit or not.
     * 
     * @param finalHit      <code>true</code> if it's a critical hit; 
     *                      <code>false</code> otherwise.
     */
    public static void setFinalHit(boolean finalHit){
        Shot.finalHit = finalHit;
    }
    
    /**
     * Perform an attack.
     * <p>
     * Calls the communication protocol to access the server and 
     * make an attack.
     * 
     * @return      <code>true</code> if successful; 
     *              <code>false</code> otherwise.
     */
    public static boolean fire(){
        Shot.marked = false;
        if(Protocol.fireShot(Game.getID(), Authenticated.getID(), row, col)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Receive an attack.
     * <p>
     * Calls the communication protocol to access the server and 
     * receive an attack.
     * 
     * @return      <code>true</code> if successful; 
     *              <code>false</code> otherwise.
     */
    public static boolean receive(){
        if(Protocol.receiveShot(Game.getID(), Authenticated.getID())){
            Shot.marked = false;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Returns the shot row.
     * <p>
     * This method always returns successfully.
     * 
     * @return  shot row.
     */
    public static int getRow(){
        return row;
    }
    
    /**
     * Returns the shot column.
     * <p>
     * This method always returns successfully.
     * 
     * @return  shot column.
     */
    public static int getCol(){
        return col;
    }
    
    /**
     * Returns if the shot is ready to be sent.
     * <p>
     * This method always returns successfully.
     * 
     * @return      <code>true</code> if it's marked; 
     *              <code>false</code> otherwise.
     */
    public static boolean isMarked(){
        return Shot.marked;
    }
    
    /**
     * Returns if the shot was a hit or not.
     * <p>
     * This method always returns successfully.
     * 
     * @return      <code>true</code> if it was a hit; 
     *              <code>false</code> otherwise.
     */
    public static boolean isHit(){
        return Shot.hit;
    }
    
    /**
     * Returns if the shot was a critical hit or not.
     * <p>
     * This method always returns successfully.
     * 
     * @return      <code>true</code> if it was a critical hit; 
     *              <code>false</code> otherwise.
     */
    public static boolean isCriticalHit(){
        return Shot.criticalHit;
    }
    
    /**
     * Returns if the shot was the final hit or not.
     * <p>
     * This method always returns successfully.
     * 
     * @return      <code>true</code> if it was the final hit; 
     *              <code>false</code> otherwise.
     */
    public static boolean isFinalHit(){
        return Shot.finalHit;
    }

}
