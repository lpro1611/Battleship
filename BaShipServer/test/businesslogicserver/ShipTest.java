/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CPU TOSH
 */
public class ShipTest {
    
    /**
     * Test of hit method, of class Ship.
     */
    @Test
    public void testHit() {
        System.out.println("hit");
        Ship instance = new Ship(2);
        int result = instance.hit();
        assertEquals(2 , instance.size);
        assertEquals(0, result); 
        result = instance.hit();
        assertEquals(1, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
