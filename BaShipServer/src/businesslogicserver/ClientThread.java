/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Rafael Kraemer
 */

public class ClientThread implements Runnable{
    
    private Socket socket;
    
    public ClientThread(Socket s){
        
        socket=s;
    }
    
    @Override
    public void run() {
        try{
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            
            while(true){
                if (in.hasNext()){
                String input = in.nextLine();
                System.out.println("Client said: "+ input);
                out.println("You said: " +input);
                out.flush();
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
    }
    }
}