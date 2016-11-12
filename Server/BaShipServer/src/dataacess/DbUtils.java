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
    
    public int registerPlayer(String email, String name, String pass, Properties props) throws SQLException {
        Connection con = openConnection(props);
        String insertPlayer = "INSERT INTO player (email, name, pass) values (?, ?, ?)";
        PreparedStatement prepSt = con.prepareStatement(insertPlayer);
        prepSt.setString(1, email);
        prepSt.setString(2, name);
        prepSt.setString(3, pass);
        
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT id FROM player WHERE name = '" + name + "'");

            if (rs.next()) {
                throw new SQLException("duplicated");
            }

            prepSt.executeUpdate();
        } finally {
            closeConnection(con);
        }
        
        return 0;
    }
}
