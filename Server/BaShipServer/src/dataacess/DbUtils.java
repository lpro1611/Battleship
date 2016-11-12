/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author diogo
 */
public class DbUtils {
    public static Connection openConnection(Properties props) throws SQLException {
        String user = props.getProperty("dbuser");
        String password = props.getProperty("dbpass");
        String url = props.getProperty("dburl");
        
        return DriverManager.getConnection(url, user, password); 
    }
    
    public static void closeConnection(Connection con) throws SQLException {
        con.close();
    }
    
    
}
