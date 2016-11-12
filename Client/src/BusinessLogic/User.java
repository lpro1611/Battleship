/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import clientsocket.*;

/**
 *
 * @author diogo
 */
public class User {
    private int id;
    private static int numberOfUsers = 0;
    
    public User(){
        id = numberOfUsers++;  //suposto o server dar o id - mudar esta linha
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int id){
        this.id=id;
    }
    
    public static int getNumberOfUsers(){
        return numberOfUsers;
    }
    
    public static void setNumberOfUsers(int numberOfUsers){
        User.numberOfUsers=numberOfUsers;
    }
}
