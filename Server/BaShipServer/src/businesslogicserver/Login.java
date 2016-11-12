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
 *
 * @author Alunos-i221-16
 */
public class Login {
    public static int verify(String name, String pass, Properties props) throws SQLException, NotFoundException, WrongPasswordException {
        DbLogin dblogin = new DbLogin();
        
        return dblogin.verifyPlayer(name, pass, props);
    }
    
    public static int register(String email, String name, String pass, Properties props) throws SQLException, DuplicatedNameException, NotFoundException {
        DbLogin dblogin = new DbLogin();
        
        return dblogin.registerPlayer(email, name, pass, props);
    }
}
