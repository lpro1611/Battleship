/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

/**
 *
 * @author diogo
 */
public class Authenticated extends User {
    private String email= "";
    private String username= "";
    private String password= "";
    
    public Authenticated(User user, String email, String username, String password){
        this.setID(user.getID());
        User.setNumberOfUsers(User.getNumberOfUsers()-1);
        this.email=email;
        this.username=username;
        this.password=password;
    }
    
    public String getUsername(){
        return username;
    }
}
