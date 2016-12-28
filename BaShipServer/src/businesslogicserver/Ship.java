/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

/**
 *This class saves the information from the Ships
 * 
 * @author CPU TOSH
 */
public class Ship {
    private int size;
    private int hits;
    /**
     * Constructor for the ship
     * <p>
     * It initializes the size of the ship, 
     * with the indicated size.
     * Then start the hit counter at zero.
     * 
     * @param size desired size of ship
     */
    public Ship(int size) {
        this.size = size;
        this.hits = 0;    
    } 
    
    
    /**
     * Gets the size of the ship
     * @return ship size
     */
    public int getSize(){
        return size;
    }
    
    /**
     * This method increments the hit counter.
     * return the aumount of hits suffered. 
     * @return nuber of hit suffered 
     */
    public int hit() {
        if (size == (++hits)) {
            return 1;
        } else {
            return 0;
        }
    }
}
