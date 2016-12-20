package dataacess;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author diogo
 */
class DbUtils {
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
