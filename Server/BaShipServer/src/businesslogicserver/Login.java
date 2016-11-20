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
 * Class that process the bussiness logic of login and register actions
 * 
 * @author Diogo Dinis
 * @version 1.0
 */
public class Login {
    /**
     * Verify if this player exist on Server.
     * <p>
     * This method returns always with success, when it have some
     * type of problems it throw an exception to caller method.
     * 
     * @param name  the name of the player
     * @param pass  the passsword of the player
     * @param props properties for the DB connection
     * @return      the DB identifier of the player
     * @throws SQLException Problems interacting with DB
     * @throws NotFoundException Cannot found the ID of the player
     * @throws WrongPasswordException The password used was incorrect
     */
    public static int verify(String name, String pass, Properties props) throws SQLException, NotFoundException, WrongPasswordException {
        DbLogin dblogin = new DbLogin();
        
        return dblogin.verifyPlayer(name, pass, props);
    }
    
    /**
     * Create a new player for play this game.
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
     * @throws DuplicatedNameException  The name already exist
     * @throws NotFoundException annot found the ID of the player 
     */
    public static int register(String email, String name, String pass, Properties props) throws SQLException, DuplicatedNameException, NotFoundException {
        DbLogin dblogin = new DbLogin();
        
        return dblogin.registerPlayer(email, name, pass, props);
    }
}
