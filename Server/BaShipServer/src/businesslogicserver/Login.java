/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package businesslogicserver;

import dataacess.DbLogin;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.*;
import java.util.Properties;

/**
 * Class that process the bussiness logic of login and register actions.
 * 
 * @author Diogo Dinis
 * @version 1.0
 */
public class Login {
    /**
     * Verify if this player exists on Server.
     * <p>
     * When this method has some type of problems it throws an 
     * exception.
     * 
     * @param name  Name of the player.
     * @param pass  Passsword of the player.
     * @param props Properties for the DB connection.
     * @return      DB identifier of the player.
     * @throws SQLException Problems interacting with DB.
     * @throws NotFoundException Cannot find the ID of the player.
     * @throws WrongPasswordException The password used was incorrect.
     */
    public static int verify(String name, String pass, Properties props) throws SQLException, NotFoundException, WrongPasswordException {
        DbLogin dblogin = new DbLogin();
        
        return dblogin.verifyPlayer(name, pass, props);
    }
    
    /**
     * Create a new player for play this game.
     * <p>
     * When this method has some type of problems it throws an 
     * exception.
     * 
     * @param email E-mail address of new player.
     * @param name  Name of the new player.
     * @param pass  Password for new player login.
     * @param props Properties for DB connection.
     * @return      DB identifier for the player.
     * @throws SQLException Problems interacting with DB.
     * @throws DuplicatedNameException  The name already exists.
     * @throws NotFoundException annot find the ID of the player.
     */
    public static int register(String email, String name, String pass, Properties props) throws SQLException, DuplicatedNameException, NotFoundException {
        DbLogin dblogin = new DbLogin();
        
        return dblogin.registerPlayer(email, name, pass, props);
    }
}
