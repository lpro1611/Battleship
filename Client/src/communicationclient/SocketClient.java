/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationclient;

import java.io.*;
import java.net.*;
/**
 *
 * @author Alunos-i221-16
 */
public class SocketClient {
    Socket bsSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    
    public void openCom() throws UnknownHostException, IOException{
        bsSocket = new Socket("localhost", 4020);/* aqui é necessário por o hostname do servidor */
        //System.out.println("Connected to server.");
        out = new PrintWriter(bsSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(bsSocket.getInputStream()));
    }
    
    public void write(String write){
        out.println(write);
    }
    
    public String read() throws IOException{
        return in.readLine();
    }

    public void closeCom() throws IOException{
        if (out != null)
            out.close();
        if (in != null)
            in.close();
        if (bsSocket != null)
            bsSocket.close();
    }
}
