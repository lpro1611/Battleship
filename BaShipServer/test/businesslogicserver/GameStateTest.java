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
        int boatid = 1;
        int startx = 1;
        int starty = 2;
        int endx = 1;
        int endy = 3;
        GameState instance = new GameState(2, "Afonso","Diogo");
        instance.placeShip("Afonso", boatid, startx, starty, endx, endy);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(0,instance.Board1[0][7]);
        assertEquals(1,instance.Board1[1][2]);
        assertEquals(1,instance.Board1[1][3]);
        assertEquals(0,instance.Board1[1][4]);
       //teste another boat 
       boatid = 5;
       startx = 1;
       starty = 2;
       endx = 5;
       endy = 2;
       instance.placeShip("Diogo", boatid, startx, starty, endx, endy);
       
       assertEquals(0,instance.Board2[0][7]);
       assertEquals(5,instance.Board2[1][2]);
       assertEquals(5,instance.Board2[2][2]);
       assertEquals(5,instance.Board2[3][2]);
       assertEquals(5,instance.Board2[4][2]);
       assertEquals(5,instance.Board2[5][2]);
       assertEquals(0,instance.Board2[5][1]);
       assertEquals(0,instance.Board2[6][2]);
    }

    /**
     * Test of attack method, of class GameState.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        GameState instance =  new GameState(2, "Afonso","Diogo");
        instance.placeShip("Afonso", 1, 1, 2, 1, 3);
        instance.placeShip("Afonso", 2, 2, 2, 2, 4);
        instance.placeShip("Diogo", 5, 1, 2, 5, 2);
        int result = instance.attack("Afonso", 0, 0);
        int result2 = instance.attack("Afonso", 1, 2);
        instance.attack("Afonso", 2, 2);
        instance.attack("Afonso", 3, 2);
        instance.attack("Afonso", 4, 2);
        int result22 = instance.attack("Afonso", 5, 2);
        int result3 = instance.attack("Diogo", 1, 2);
        int result33 = instance.attack("Diogo", 1, 3);
        int result4 = instance.attack("Diogo", 2, 2);
        int result44 = instance.attack("Diogo", 2, 3);
        int result444 = instance.attack("Diogo", 2, 5);
        int result4444 = instance.attack("Diogo", 2, 4);
        assertEquals(0, result);
        assertEquals(0, result2);
        assertEquals(1, result22);
        assertEquals(0, result3);
        assertEquals(1, result33);
        assertEquals(1, result4);
        assertEquals(1, result44);
        assertEquals(1, result444);
        assertEquals(2, result4444);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        String expResult2 = "Begin";
        String result = instance.PlayerReady(player);
        assertEquals(expResult, result);
        assertEquals(true, instance.ready1);
        assertEquals(false, instance.ready2);
        result = instance.PlayerReady("Diogo");
        assertEquals(expResult2, result);
        assertEquals(true, instance.ready1);
        assertEquals(true, instance.ready2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
