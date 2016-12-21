package dataacess;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author diogo
 */
// Singletone
public enum DbUtils {
    INSTANCE;
    
    private static String user;
    private static String password;
    private static String url;
    
    private DbUtils() {}
    
    public void setConnectionParameters(Properties props) {
        user = props.getProperty("dbuser");
        password = props.getProperty("dbpass");
        url = props.getProperty("dburl");
    }
    
    public static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    public static void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
}
