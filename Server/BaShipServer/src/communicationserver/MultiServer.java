/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package communicationserver;

import java.net.*;
import java.io.*;
import java.sql.*;

/**
 *
 * @author Alunos-i221-16
 */
public class MultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
 
        try {
            Class.forName("org.postgresql.Driver");
        } catch ( ClassNotFoundException e) {
            System.out.println("Can't found postgres Driver");
            e.printStackTrace();
        }
        
        String user = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost/baship";
        Connection con = null;
        
        try {
            con = DriverManager.getConnection(url, user, password); 
        } catch (SQLException e) {
            System.out.println("Can't connect SQL data base");
            e.printStackTrace();
        }
        
        try {
             serverSocket = new ServerSocket(4020);
        } catch (IOException e) {
             System.err.println("Could not listen on port: 4020.");
             System.exit(-1);
        }
 
        while (listening) {
            new MultiServerThread(serverSocket.accept()).start();
        }
 
        serverSocket.close();
    }
}
