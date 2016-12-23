/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

import dataacess.DbUser;
import exceptions.NotFoundException;
import java.net.*;
import java.sql.SQLException;

/**
 *
 * @author diogo
 */
public class Authenticated {
    int id;
    String name;
    Socket socket;
    boolean inGame;
    
    public Authenticated(int id) throws SQLException, NotFoundException {
        this.id = id;
        name = DbUser.getUserNameById(id);
        inGame = false;
    }
    
    public void addGame () {}
    public void removeGame () {}
}
