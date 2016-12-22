/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess;

import exceptions.NotFoundException;
import java.sql.SQLException;
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
public class DbUserTest {
    
    public DbUserTest() {
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
     * Test of getUserNameById method, of class DbUser.
     */
    @Test
    public void testGetUserNameById() {
        System.out.println("getUserNameById");
        int playerId = 4;
        String expResult = "bifox73";
        String result = null;
        
        try {
            result = DbUser.getUserNameById(playerId);
        } catch (SQLException | NotFoundException ex) {
            Logger.getLogger(DbUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(expResult, result);
    }
    
}
