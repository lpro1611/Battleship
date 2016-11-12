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
    public static void main(String[] a) throws IOException {
 
    Socket bsSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
 
    try {
        bsSocket = new Socket("bloco-cinza", 4020);/* aqui é necessário por o hostname do servidor */
        System.out.println("Connected to server.");
        out = new PrintWriter(bsSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(bsSocket.getInputStream()));
    } catch (UnknownHostException e) {
        System.err.println("Don't know about host: localhost.");
        System.exit(1);
    } catch (IOException e) {
        System.err.println("Couldn't get I/O for the connection to: bloco-cinza.");
        System.exit(1);
    }
   
    out.println("login#diogo#pass_muito_ma");
 
    out.close();
    in.close();
    bsSocket.close();
    }
}
