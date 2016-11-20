/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess;

import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.*;
import java.util.Properties;
import javafx.util.Pair;


/**
 * Class that do the DB access to get, set and update data in DB
 * for login and register actions
 * 
 * @author Diogo Dinis
 * @version 1.0
 * 
 */
public class DbLogin {
    /**
     * Creates a new player for play this game.
     * <p>
     * The password should arrive encrypted, because this method 
     * didn't do any king of encryption.
     * <p>
     * This method returns always with success, when it have some
     * type of problems it throw an exception to caller method.
     * 
     * @param email an e-mail address of new player
     * @param name  the name of the new player
     * @param pass  the password for new player log in
     * @param props properties for DB connection
     * @return      the DB identifier for the player
     * @throws SQLException Problems interacting with DB
     * @throws DuplicatedNameException  The name already exist in DB
     * @throws NotFoundException   Cannot found the ID of the player in DB
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
     * Verify if this player exists in the DB.
     * <p>
     * This method returns always with success, when it have some
     * type of problems it throw an exception to caller method.
     * 
     * @param name  the name of the player
     * @param pass  the passsword of the player
     * @param props properties for the DB connection
     * @return      the DB identifier of the player
     * @throws SQLException Problems interacting with DB
     * @throws NotFoundException Cannot found the ID of the player in DB 
     * @throws WrongPasswordException The password used was incorrect
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