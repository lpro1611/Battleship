package dataacess;


import dataacess.auxiliarstructs.*;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.*;
import java.util.ArrayList;
import javafx.util.Pair;


/**
 * Accesses the DB to get, set and update data for login and
 * register actions.
 * 
 * @author Diogo Dinis
 * 
 */
public class DbLogin {
    
    /**
     * Class Constructor
     */
    public DbLogin() {}
    
    /**
     * Creates a new user.
     * <p>
     * The password should arrive encrypted, because this method 
     * doesn't do any kind of encryption.
     * 
     * @param email                     e-mail address of the new user
     * @param name                      name of the new user
     * @param pass                      password of the new user
     * @return                          user's DB identifier
     * @throws SQLException             problems interacting with the DB
     * @throws DuplicatedNameException  the name already exists in the DB
     * @throws NotFoundException        cannot find user's ID in the DB
     */
    public static int registerPlayer(String email, String name, String pass) throws SQLException, DuplicatedNameException, NotFoundException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String insertPlayer = "INSERT INTO users (email, name, pass) VALUES (?, ?, ?)";
        
        try {
            if (checkIfNameExists(name, conn)) {
                throw new DuplicatedNameException();
            }

            try (PreparedStatement prepSt = conn.prepareStatement(insertPlayer)) {
                prepSt.setString(1, email);
                prepSt.setString(2, name);
                prepSt.setString(3, pass);
                prepSt.executeUpdate();
            }
            Pair<Integer, String> player = getPlayerByName(name, conn);
            
            return player.getKey();
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    /**
     * Verifies if this user exists in the DB.
     * 
     * @param name                      user's name
     * @param pass                      user's password
     * @return                          user's DB identifier
     * @throws SQLException             problems interacting with the DB
     * @throws NotFoundException        cannot find user's ID in the DB
     * @throws WrongPasswordException   password used was incorrect
     */
    public static int verifyPlayer(String name, String pass) throws SQLException, NotFoundException, WrongPasswordException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        
        try { 
            Pair<Integer, String> player = getPlayerByName(name, conn);
            
            if (player.getValue().equals(pass)) {
                return player.getKey();
            }
            throw new WrongPasswordException();
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    private static Pair<Integer, String> getPlayerByName(String name, Connection conn) throws SQLException, NotFoundException {
        String selectPlayer = "SELECT id, pass FROM users WHERE name = ?";
        
        try (PreparedStatement prepSt = conn.prepareStatement(selectPlayer)) {
            prepSt.setString(1, name);
            
            try (ResultSet rs = prepSt.executeQuery()) {
                if (rs.next()) {
                    return new Pair<Integer, String>(rs.getInt("id"), rs.getString("pass"));
                }
            }
        }
        
        throw new NotFoundException();
    }
    
    private static boolean checkIfNameExists(String name, Connection conn) throws SQLException {
        String checkPlayer = "SELECT id FROM users WHERE name = ?";
        
        try (PreparedStatement prepSt = conn.prepareStatement(checkPlayer)) {
            prepSt.setString(1, name);
            
            try (ResultSet rs = prepSt.executeQuery()) {
                return rs.next();
            }
        }
    }
}