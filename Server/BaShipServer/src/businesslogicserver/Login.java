/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package businesslogicserver;

import javafx.util.Pair;

/**
 *
 * @author Alunos-i221-16
 */
public class Login {
    public final Pair<String,String> login;
        
    public Login(Pair<String,String> login){
        this.login = login;
    }
     
    public String VerifyLogin(){
        System.out.println("key: " + login.getKey() + "\nvalue: " + login.getValue() + "\n");
        return "true"; 
    }
}
