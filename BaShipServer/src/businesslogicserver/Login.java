package businesslogicserver;

import dataacess.*;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.*;

/**
 * Processes the business logic for the login and register actions.
 * 
 * @author Diogo Dinis
 */
public class Login {
    
    /**
     * Class Constructor
     */
    public Login() {}
    
    /**
     * Verifies if this user exists on the data base.
     * <p>
     * Returns the user's id, but when someone else is already logged in returns -1.
     * 
     * @param name                      name of the user
     * @param pass                      password of the user
     * @return                          user's DB identifier 
     * @throws SQLException             problems interacting with DB
     * @throws NotFoundException        cannot find the user's ID
     * @throws WrongPasswordException   password used was incorrect
     */
    public static int verify(String name, String pass) throws SQLException, NotFoundException, WrongPasswordException {
        int id = DbLogin.verifyPlayer(name, pass);
        
        boolean duplicatedLogin = AuthenticatedUsers.add(id);
        
        if (duplicatedLogin) {
            return -1;
        }
        
        return id;
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
     * @return                              user's DB identifier
     * @throws SQLException                 problems interacting with the DB
     * @throws DuplicatedNameException      the name already exists
     * @throws NotFoundException            cannot find the user's ID
     */
    public static int register(String email, String name, String pass) throws SQLException, DuplicatedNameException, NotFoundException {
        return DbLogin.registerPlayer(email, name, pass);
    }
}
