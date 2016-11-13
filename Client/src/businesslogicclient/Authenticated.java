/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicclient;

/**
 *
 * @author diogo
 */
public class Authenticated {
    private static int id;
    private static String username = "";
    
    public Authenticated(int id, String username){
        Authenticated.id = id;
        Authenticated.username = username;
    }
    
    public static int getID(){
        return id;
    }
    
    public static String getUsername(){
        return username;
    }
    
    public static void setID(int id){
        Authenticated.id = id;
    }
    
    public static void setUsername(String username){
        Authenticated.username = username;
    }
    
}
