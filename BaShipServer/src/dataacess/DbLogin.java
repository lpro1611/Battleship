package dataacess;

import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.*;
import java.util.Properties;
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
    public DbLogin(){}
    
    /**
     * Creates a new user.
     * <p>
     * The password should arrive encrypted, because this method 
     * doesn't do any kind of encryption.
     * 
     * @param email                     e-mail address of the new user
     * @param name                      name of the new user
     * @param pass                      password of the new user
     * @param props                     DB's connection properties
     * @return                          user's DB identifier
     * @throws SQLException             problems interacting with the DB
     * @throws DuplicatedNameException  the name already exists in the DB
     * @throws NotFoundException        cannot find user's ID in the DB
     */
    public int registerPlayer(String email, String name, String pass, Properties props) throws SQLException, DuplicatedNameException, NotFoundException {
        Connection con = DbUtils.openConnection(props);
        String insertPlayer = "INSERT INTO player (email, name, pass) values (?, ?, ?)";
        
        try {
            if (checkIfNameExists(name, con)) {
                throw new DuplicatedNameException();
            }

            try (PreparedStatement prepSt = con.prepareStatement(insertPlayer)) {
                prepSt.setString(1, email);
                prepSt.setString(2, name);
                prepSt.setString(3, pass);
                prepSt.executeUpdate();
            }
            Pair<Integer, String> player = getPlayerByName(name, con);
            return player.getKey();
        } finally {
            DbUtils.closeConnection(con);
        }
    }
    
    /**
     * Verifies if this user exists in the DB.
     * 
     * @param name                      name of the user
     * @param pass                      password of the user
     * @param props                     DB's connection properties
     * @return                          user's DB identifier
     * @throws SQLException             problems interacting with the DB
     * @throws NotFoundException        cannot find user's ID in the DB
     * @throws WrongPasswordException   password used was incorrect
     */
    public int verifyPlayer(String name, String pass, Properties props) throws SQLException, NotFoundException, WrongPasswordException {
        Connection con = DbUtils.openConnection(props);
        
        try { 
            Pair<Integer, String> player = getPlayerByName(name, con);
            
            if (player.getValue().equals(pass)) {
                return player.getKey();
            }
            throw new WrongPasswordException();
        } finally {
            DbUtils.closeConnection(con);
        }
    }
    
    private Pair<Integer, String> getPlayerByName(String name, Connection con) throws SQLException, NotFoundException {
        String selectPlayer = "SELECT id, pass FROM player WHERE name = ?";
        try (PreparedStatement prepSt = con.prepareStatement(selectPlayer)) {
            prepSt.setString(1, name);
            try (ResultSet rs = prepSt.executeQuery()) {
                if (rs.next()) {
                    return new Pair<Integer, String>(rs.getInt("id"), rs.getString("pass"));
                }
            }
        }
        throw new NotFoundException();
    }
    
    private boolean checkIfNameExists(String name, Connection con) throws SQLException {
        String checkPlayer = "SELECT id FROM player WHERE name = ?";
        try (PreparedStatement prepSt = con.prepareStatement(checkPlayer)) {
            prepSt.setString(1, name);
            try (ResultSet rs = prepSt.executeQuery()) {
                return rs.next();
            }
        }
    }
}