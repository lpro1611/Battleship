package businesslogicserver;

import dataacess.DbLogin;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.*;
import java.util.Properties;

/**
 * Processes the business logic for the login and register actions.
 * 
 * @author Diogo Dinis
 */
public class Login {
    
    /**
     * Class Constructor
     */
    public Login(){}
    
    /**
     * Verifies if this user exists on the data base.
     * 
     * @param name                      name of the user
     * @param pass                      password of the user
     * @param props                     DB's connection properties
     * @return                          user's DB identifier
     * @throws SQLException             problems interacting with DB
     * @throws NotFoundException        cannot find the user's ID
     * @throws WrongPasswordException   password used was incorrect
     */
    public static int verify(String name, String pass, Properties props) throws SQLException, NotFoundException, WrongPasswordException {
        return DbLogin.verifyPlayer(name, pass, props);
    }
    
    /**
     * Registers a new user.
     * <p>
     * When this method has some type of problems it throws an 
     * exception.
     * 
     * @param email                         e-mail address of new user
     * @param name                          name of the new user
     * @param pass                          password for new user login
     * @param props                         DB's connection properties
     * @return                              user's DB identifier
     * @throws SQLException                 problems interacting with the DB
     * @throws DuplicatedNameException      the name already exists
     * @throws NotFoundException            cannot find the user's ID
     */
    public static int register(String email, String name, String pass, Properties props) throws SQLException, DuplicatedNameException, NotFoundException {
        return DbLogin.registerPlayer(email, name, pass, props);
    }
}
