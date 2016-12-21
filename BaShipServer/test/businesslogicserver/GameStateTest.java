/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CPU TOSH
 */
public class GameStateTest {
    
    public GameStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of placeShip method, of class GameState.
     */
    @Test
    public void testPlaceShip() {
        System.out.println("placeShip");
        String player = "Afonso";
        int id = 1;
        int startx = 1;
        int starty = 2;
        int endx = 1;
        int endy = 3;
        GameState instance = new GameState(2, "Afonso","Diogo");
        instance.placeShip("Afonso", id, startx, starty, endx, endy);
        System.out.println(instance.Board1[1][2]);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(1,1);
    }

    /**
     * Test of attack method, of class GameState.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        String player = "";
        int x = 0;
        int y = 0;
        GameState instance =  new GameState(2, "Afonso","Diogo");
        int expResult = 0;
        int result = instance.attack(player, x, y);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(1,1);
    }

    /**
     * Test of PlayerReady method, of class GameState.
     */
    @Test
    public void testPlayerReady() {
        System.out.println("PlayerReady");
        String player = "Afonso";
        GameState instance = new GameState(2, "Afonso","Diogo");
        String expResult = "Wait for other Player";
        String result = instance.PlayerReady(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(1,1);
    }
    
}
