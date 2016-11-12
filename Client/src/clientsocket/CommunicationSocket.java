/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientsocket;

import java.io.*;
import java.net.*;
/**
 *
 * @author Alunos-i221-16
 */
public class CommunicationSocket {
    Socket bsSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    
    public void openCom() throws UnknownHostException, IOException{
        bsSocket = new Socket("localhost", 4020);/* aqui é necessário por o hostname do servidor */
        //System.out.println("Connected to server.");
        out = new PrintWriter(bsSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(bsSocket.getInputStream()));
    }
    
    public boolean validateLogin(String username, String password){
        out.println("login#"+ username +"#"+password);
        //insert code here
        return false;
    }
    
    public String validateRegister(String email, String username, String password){
        out.println("register#"+ email +"#"+username +"#"+password);
        //insert code here
        return "Ok";
    }

    public void closeCom() throws IOException{
        out.close();
        in.close();
        bsSocket.close();
    }
}
