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
    private final int id;
    private final String name;
    private Socket socket;
    private boolean inGame;
    
    public Authenticated(int id) throws SQLException, NotFoundException {
        this.id = id;
        name = DbUser.getUserNameById(id);
        inGame = false;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean getInGame() {
        return inGame;
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
    public void setSocket(Socket socket) {
        this.socket = socket;
    } 
    
    public void addGame() {}
    public void removeGame() {}
}
