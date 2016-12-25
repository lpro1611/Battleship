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
    private int currentGameId;
    
    public Authenticated(int id) throws SQLException, NotFoundException {
        this.id = id;
        name = DbUser.getUserNameById(id);
        currentGameId = -1;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCurrentGameId() {
        return currentGameId;
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public void setCurrentGameId(int currentGameId) {
        if (currentGameId > -1) {
            this.currentGameId = currentGameId;
        }
    }
    
    public void clearCurrentGameId() {
        currentGameId = -1;
    }
    
    public void setSocket(Socket socket) {
        this.socket = socket;
    } 
    
    public void addGame() {}
    public void removeGame() {}
}
