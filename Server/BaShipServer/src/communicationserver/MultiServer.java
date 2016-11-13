/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package communicationserver;

import dataacess.DbLogin;
import exceptions.DuplicatedNameException;
import java.util.Properties;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos-i221-16
 */
public class MultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        Properties props = new Properties();
        
        props.setProperty("dburl", "jdbc:postgresql://localhost/baship");
        props.setProperty("dbpass", "postgres");
        props.setProperty("dbuser", "postgres");
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch ( ClassNotFoundException e) {
            System.out.println("Can't found postgres Driver");
            e.printStackTrace();
        }
        
        try {
             serverSocket = new ServerSocket(4020);
        } catch (IOException e) {
             System.err.println("Could not listen on port: 4020.");
             System.exit(-1);
        }
 
        while (listening) {
            new MultiServerThread(serverSocket.accept(), props).start();
        }
 
        serverSocket.close();
        
    }
}
