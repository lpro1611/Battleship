package communicationclient;

import businesslogicclient.Authenticated;
import businesslogicclient.Game;
import businesslogicclient.Shot;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;


/**
 * Implements the communication protocol between
 * the server and the client.
 * @author Diogo Recharte
 */
public class Protocol {
    private static final String TOKEN = "#";
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String SOCKET = "socket";
    private static final String EXIT = "exit";
    private static final String GAME = "game";
    private static final String BEGIN = "begin";
    private static final String ATTACK = "attack";
    private static final String CREATE = "create";
    private static final String PLACE = "place";
    
    /**
     * Class Constructor
     */
    public Protocol(){}
    
    /**
     * Prepares and sends the message to the server, to validate the 
     * login data.
     * <p>
     * This method receives a username and a password and combines them 
     * with the login opcode to generate a login message for the server.
     * Afterwards it sends the message, awaits the response from the 
     * server and passes it to be interpreted.
     * 
     * @param username  username attempting to log in
     * @param password  password attempting to log in
     */
    public static int validateLogin(String username, String password){
        SocketClient cSocket = new SocketClient();
        String inputLine;
        String[] reply;
        int userID = -1;
        try{ 
            cSocket.openCom();
            
            cSocket.write(LOGIN + TOKEN + username + TOKEN + password);
            
            inputLine=cSocket.read();
            
            reply=decodeReply(inputLine, LOGIN);
            if(reply[0].equals("ok"))
                userID = Integer.parseInt(reply[1]);
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        } finally{
            try {
                cSocket.closeCom();
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to gnomo.");
            }
        }
        return userID;
    }
    
   /**
    * Prepares and sends the message to the server, to validate the 
    * register data.
    * <p>
    * This method receives a username, a password and an email address 
    * and combines them with the register opcode to generate a register 
    * message for the server. Afterwards it sends the message, awaits 
    * the response from the server and passes it to be interpreted.
    * <p>
    * @param email      email address to register
    * @param username   username to register
    * @param password   password to register
    */
    public static int validateRegister(String email, String username, String password){
        SocketClient cSocket = new SocketClient();
        String inputLine;
        String[] reply;
        int userID = -1;
        
        try{ 
            cSocket.openCom();
            
            cSocket.write(REGISTER + TOKEN + email + TOKEN +  username + TOKEN + password);
            
            inputLine=cSocket.read();
            
            reply=decodeReply(inputLine, REGISTER);
            if(reply[0].equals("ok"))
                userID = Integer.parseInt(reply[1]);
            else if (reply[0].equals("duplicated"))
                userID = -2;
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        } finally{
            try {
                cSocket.closeCom();
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to gnomo.");
            }
        }
        
        return userID;
        
    }
    
    public static void startComs(){
        SocketClient clientSocket = new SocketClient();
        SocketClient serverSocket = new SocketClient();
        boolean clientFlag = false;
        boolean serverFlag = false;
        String inputLine;
        String[] reply;
        
        try{ 
            clientSocket.openCom();
            clientFlag=true;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
            
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        try{
            serverSocket.openCom();
            serverSocket.write(SOCKET + TOKEN + Authenticated.getID());
            inputLine=serverSocket.read();
            
            reply=decodeReply(inputLine, SOCKET);
            if(reply[0].equals("ok"))
                serverFlag=true;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        if(clientFlag && serverFlag){
            Authenticated.setClientSocket(clientSocket);
            Authenticated.setServerSocket(serverSocket);
        }
        else{
            System.err.println("Couldn't connect to host gnomo.");
            System.exit(1);
        }
    }
    
    public static void endComs(){
        try {
            Authenticated.getClientSocket().write(EXIT + TOKEN + Authenticated.getID());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.err.println("Thread interrupted");
            } finally{
            Authenticated.getClientSocket().closeCom();
            Authenticated.getServerSocket().closeCom();
            }
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        finally{
            Authenticated.setClientSocket(null);
            Authenticated.setServerSocket(null);
        }
    }
        
    public static boolean findGame(int userID){
        
        String inputLine = null;
        String[] reply;
        Game game;
        
        /*Authenticated.getClientSocket().write(GAME + TOKEN + CREATE + TOKEN + userID);

        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(CREATE)){
                if(reply[1].equals("ok")){
                    Game.reset();
                    Game.setID(Integer.parseInt(reply[2]));
                    Game.setOpponent(reply[3]);
                    return true;
                }
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        return false;*/
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        Game.setOpponent("test_opponent");
        Game.setID(1);
        return true;
    }
    
    
    public static boolean placeShip(int gameID, int userID, int rowStart, int colStart, int rowEnd, int colEnd){
        
        String inputLine;
        String[] reply;
        
        /*Authenticated.getClientSocket().write(GAME + TOKEN + PLACE + TOKEN + gameID + TOKEN + userID + TOKEN + rowStart + TOKEN + colStart + TOKEN + rowEnd + TOKEN + colEnd);

        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(PLACE)){
                if(reply[1].equals("ok")){
                    return true;
                }
                else
                    return false;
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        
        return false;*/
        return true;
    }
    
    public static boolean beginGame(int gameID, int userID){
        
        String inputLine;
        String[] reply;
        
        /*Authenticated.getClientSocket().write(GAME + TOKEN + BEGIN + TOKEN + gameID + TOKEN + userID);

        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(BEGIN)){
                if(reply[1].equals("start")){
                    Game.setMyTurn(true);
                    return true;
                }
                else if(reply[1].equals("wait")){
                    Game.setMyTurn(false);
                    return true;
                }
                else
                    return false;
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        
        return false;*/
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        Game.setMyTurn(true);
        return true;
    }
    public static boolean fireShot(int gameID, int userID, int row, int col){
        
        String inputLine;
        String[] reply;
        
        /*Authenticated.getClientSocket().write(GAME + TOKEN + ATTACK + TOKEN + gameID + TOKEN + userID + TOKEN + row + TOKEN + col);

        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(ATTACK)){
                if(reply[1].equals("hit")){
                    Shot.setHit(true);
                    Shot.setCriticalHit(false);
                    return true;
                }
                else if(reply[1].equals("criticalhit")){
                    Shot.setHit(true);
                    Shot.setCriticalHit(true);
                    Shot.setBoadName(reply[4]);
                    return true;
                }
                else if(reply[1].equals("miss")){
                    Shot.setHit(false);
                    return true;
                }
                else{
                    return false;
                }
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        
        return false;*/
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        Shot.setHit(true);
        Shot.setCriticalHit(false);
        return false;
    }
    
    /**
     * Interprets the messages received from the server.
     * <p>
     * This method receives the message sent by the server as well as the 
     * expected opcode. It then checks the validity of this reply and
     * saves the important data to be used by the business logic.
     * 
     * @param input         message received from the server
     * @param operation     opcode to validate the message
     */
     private static String[] decodeReply(String input, String operation){
        String opcode[] = input.split(TOKEN);
        if (opcode[0].equals(operation)){
            return Arrays.copyOfRange(opcode, 1, opcode.length);
        }
        else {
            System.out.println("Error communicating with server");
            return null;                                                // secalhar mudar isto
        }
    }
    
}
