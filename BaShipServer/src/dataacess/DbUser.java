package dataacess;

import exceptions.NotFoundException;
import java.sql.*;

/**
 * Accesses the DB to get, set and update data from user.
 * 
 * @author diogo
 */
public class DbUser {
    
    /**
     * Class Construtor
     */
    public DbUser() {}
    
    /**
     * Get user's name using it DB identifier
     * 
     * @param playerId              user's DB identifier
     * @return                      user's name
     * @throws SQLException         problems interacting with the DB
     * @throws NotFoundException    cannot find user's ID in the DB
     */
    public static String getUserNameById(int playerId) throws SQLException, NotFoundException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String getUserName = "SELECT name FROM users WHERE id = ?";
        
        try (PreparedStatement prepSt = conn.prepareStatement(getUserName)) {
            prepSt.setInt(1, playerId);
            
            try (ResultSet rs = prepSt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
                
                throw new NotFoundException();
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
}
