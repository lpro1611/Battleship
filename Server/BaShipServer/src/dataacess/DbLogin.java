/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess;

import com.sun.org.apache.xml.internal.utils.WrongParserException;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.*;
import java.util.Properties;
import javafx.util.Pair;


/**
 *
 * @author diogo
 */
public class DbLogin {
    public int registerPlayer(String email, String name, String pass, Properties props) throws SQLException, DuplicatedNameException, NotFoundException {
        Connection con = DbUtils.openConnection(props);
        String insertPlayer = "INSERT INTO player (email, name, pass) values (?, ?, ?)";
        PreparedStatement prepSt = con.prepareStatement(insertPlayer);
        prepSt.setString(1, email);
        prepSt.setString(2, name);
        prepSt.setString(3, pass);
        
        try {
            if (checkIfNameExists(name, con)) {
                throw new DuplicatedNameException();
            }

            prepSt.executeUpdate();
            Pair<Integer, String> player = getPlayerByName(name, con);
            return player.getKey();
            
        } finally {
            DbUtils.closeConnection(con);
        }
    }
    
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
        PreparedStatement prepSt = con.prepareStatement(selectPlayer);
        prepSt.setString(1, name);
        ResultSet rs = prepSt.executeQuery();

        if (rs.next()) {
            return new Pair<Integer, String>(rs.getInt("id"), rs.getString("pass"));
        }

        throw new NotFoundException();
    }
    
    private boolean checkIfNameExists(String name, Connection con) throws SQLException {
        String checkPlayer = "SELECT id FROM player WHERE name = ?";
        PreparedStatement prepSt = con.prepareStatement(checkPlayer);
        prepSt.setString(1, name);
        ResultSet rs = prepSt.executeQuery();

        return rs.next();
    }
}
