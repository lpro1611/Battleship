/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess;

import dataacess.auxiliarstructs.GameType;
import dataacess.auxiliarstructs.MoveType;
import dataacess.auxiliarstructs.ShipType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author diogo
 */
public class DbGameTest {
    
    public DbGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Properties props = new Properties();
        
        props.setProperty("dburl", "jdbc:postgresql://dbm.fe.up.pt/lpro1611");
        props.setProperty("dbpass", "G!7367hxf");
        props.setProperty("dbuser", "lpro1611");
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch ( ClassNotFoundException e) {
            System.out.println("Can't found postgres Driver");
        }
        
        DbUtils.INSTANCE.setConnectionParameters(props);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setShipPosition method, of class DbGame.
     */
    @Test
    public void testSetShipPosition() {
        System.out.println("setShipPosition");
        int idOnGame = 4;
        int beginX = 6;
        int beginY = 2;
        int endX = 5;
        int endY = 5;
        int playerId = 21;
        int gameId = 1;
        
        try {
            DbGame.setShipPosition(idOnGame, beginX, beginY, endX, endY, playerId, gameId);
        } catch (SQLException ex) {
            Logger.getLogger(DbGameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Test of setMovePosition method, of class DbGame.
     */
    @Test
    public void testSetMovePosition() {
        System.out.println("setMovePosition");
        int x = 3;
        int y = 6;
        boolean hit = false;
        int playerId = 21;
        int gameId = 1;
        
        try {
            DbGame.setMovePosition(x, y, hit, playerId, gameId);
        } catch (SQLException ex) {
            Logger.getLogger(DbGameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Test of createGame method, of class DbGame.
     * 
     * This method also test the getLastGameId method.
     */
    
    @Test
    public void testCreateGame() {
        System.out.println("createGame");
        
        int player1Id = 21;
        int player2Id = 4;
        int expResult = -1; // se ambos os trys falharem dá erro por que as inicializações são diferentes
        
        try {
            expResult = DbGame.getLastGameId() + 1;
        } catch (SQLException ex) {
            Logger.getLogger(DbGameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int result = 0;
        try {
            result = DbGame.createGame(player1Id, player2Id);
        } catch (SQLException ex) {
            Logger.getLogger(DbGameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(expResult, result);
    }
    

    /**
     * Test of setGameWinner method, of class DbGame.
     */
    
    @Test
    public void testSetGameWinner() {
        System.out.println("setGameWinner");
        
        int winnerId = 24;
        int gameId = 10;
        
        try {
            DbGame.setGameWinner(winnerId, gameId);
        } catch (SQLException ex) {
            Logger.getLogger(DbGameTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    

    /**
     * Test of getShipsPositionByGameId method, of class DbGame.
     */
    @Test
    public void testGetShipsPositionByGameId() {
        System.out.println("getShipsPositionByGameId");
        
        int gameId = 0;
        ArrayList<ShipType> expResult = new ArrayList<>();
        ArrayList<ShipType> result = null;
        
        expResult.add(new ShipType(1, 2, 2, 2, 5, 21, 4));
        expResult.add(new ShipType(2, 7, 3, 8, 3, 21, 4));
        expResult.add(new ShipType(3, 1, 5, 1, 7, 21, 4));
        
        try {
            result = DbGame.getShipsPositionByGameId(gameId);
        } catch (SQLException ex) {
            Logger.getLogger(DbGameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Iterator<ShipType> resultIterator = result.iterator();
        Iterator<ShipType> expResultIterator = expResult.iterator();
        
        while (expResultIterator.hasNext() && resultIterator.hasNext()) {
            ShipType resultElem = resultIterator.next();
            ShipType expResultElem = expResultIterator.next();
            
            assertEquals(resultElem.getBeginX(), expResultElem.getBeginX());
            assertEquals(resultElem.getBeginY(), expResultElem.getBeginY());
            assertEquals(resultElem.getEndX(), expResultElem.getEndX());
            assertEquals(resultElem.getEndY(), expResultElem.getEndY());
            assertEquals(resultElem.getIdOnGame(), expResultElem.getIdOnGame());
            assertEquals(resultElem.getPLayerId(), expResultElem.getPLayerId());
            assertEquals(resultElem.getGameId(), expResultElem.getGameId());
        }
    }

    /**
     * Test of getMovesByGameId method, of class DbGame.
     */
    @Test
    public void testGetMovesByGameId() {
        System.out.println("getMovesByGameId");
        
        int gameId = 4;
        ArrayList<MoveType> expResult = new ArrayList<>();
        ArrayList<MoveType> result = null;
        
        expResult.add(new MoveType(4, 1, false, 21, 4));
        expResult.add(new MoveType(9, 3, false, 24, 4));
        expResult.add(new MoveType(2, 6, true, 21, 4));
        
        try {
            result = DbGame.getMovesByGameId(gameId);
        } catch (SQLException ex) {
            Logger.getLogger(DbGameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Iterator<MoveType> resultIterator = result.iterator();
        Iterator<MoveType> expResultIterator = expResult.iterator();
        
        while (expResultIterator.hasNext() && resultIterator.hasNext()) {
            MoveType resultElem = resultIterator.next();
            MoveType expResultElem = expResultIterator.next();
            
            assertEquals(resultElem.getX(), expResultElem.getX());
            assertEquals(resultElem.getY(), expResultElem.getY());
            assertEquals(resultElem.getHit(), expResultElem.getHit());
            assertEquals(resultElem.getGameId(), expResultElem.getGameId());
            assertEquals(resultElem.getPlayerId(), expResultElem.getPlayerId());
        }
    }
    
    /**
     * Test of getGamesByPlayerId method, of class DbGame.
     */
    @Test
    public void testGetGamesByPlayerId() {
        System.out.println("getGamesByPlayerId");
        
        int playerId = 24;
        ArrayList<GameType> expResult = new ArrayList<>();
        ArrayList<GameType> result = null;
        
        expResult.add(new GameType(13, 13, 24, 13));
        expResult.add(new GameType(12, 24, 21, 21));
        expResult.add(new GameType(11, 24, 21, 21));
        expResult.add(new GameType(10, 24, 21, 24));
        expResult.add(new GameType(9, 24, 21, 21));
        expResult.add(new GameType(8, 24, 21, 21));
        expResult.add(new GameType(7, 24, 21, 21));
        expResult.add(new GameType(6, 24, 21, 0));
        expResult.add(new GameType(5, 24, 21, 0));
        expResult.add(new GameType(4, 24, 21, 0));
        expResult.add(new GameType(3, 24, 21, 0));
        expResult.add(new GameType(2, 24, 21, 0));
        
        try {
            result = DbGame.getGamesByPlayerId(playerId);
        } catch (SQLException ex) {
            Logger.getLogger(DbGameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Iterator<GameType> resultIterator = result.iterator();
        Iterator<GameType> expResultIterator = expResult.iterator();
        
        while (expResultIterator.hasNext() && resultIterator.hasNext()) {
            GameType resultElem = resultIterator.next();
            GameType expResultElem = expResultIterator.next();
            
            assertEquals(resultElem.getWinnerId(), expResultElem.getWinnerId());
            assertEquals(resultElem.getGameId(), expResultElem.getGameId());
            assertEquals(resultElem.getPlayer1Id(), expResultElem.getPlayer1Id());
            assertEquals(resultElem.getPlayer2Id(), expResultElem.getPlayer2Id());
        }
    }
}
