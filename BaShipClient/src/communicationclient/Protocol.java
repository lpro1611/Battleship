package communicationclient;

import businesslogicclient.Authenticated;
import businesslogicclient.Game;
import businesslogicclient.Shot;
import interfaces.GameGUI;
import interfaces.MainFrame;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
    private static final String END = "end";
    private static final String START = "start";
    private static final String WAIT = "wait";
    private static final String ATTACK = "attack";
    private static final String CREATE = "create";
    private static final String PLACE = "place";
    private static final String QUIT = "quit";
    private static final String CHALLENGE = "challenge";
    private static final String INVITE = "invite";
    private static final String CHAT = "chat";
    
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
     * @param username  user name attempting to log in
     * @param password  password attempting to log in
     * @return          user identification number; 
     *                  <code>-1</code> if an error occurred.
     */
    public static int validateLogin(String username, String password){
        SocketClient clientSocket = new SocketClient();
        String inputLine;
        String[] reply;
        boolean flag = true;
        int userID = -1;
        try{ 
            clientSocket.openCom();
            
            clientSocket.write(LOGIN + TOKEN + username + TOKEN + password);
            
            inputLine=clientSocket.read();
            
            reply=decodeReply(inputLine, LOGIN);
            if(reply[0].equals("ok")){
                userID = Integer.parseInt(reply[1]);
                Authenticated.setClientSocket(clientSocket);
                flag = false;                
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        } finally{
            if (flag){
                try {
                    clientSocket.closeCom();
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to gnomo.");
                }
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
    * 
    * @param email      email address to register
    * @param username   username to register
    * @param password   password to register
    * @return           user identification number; 
    *                   <code>-1</code> if an error occurred;
    *                   <code>-2</code> if username is already taken.
    */
    public static int validateRegister(String email, String username, String password){
        SocketClient clientSocket = new SocketClient();
        String inputLine;
        String[] reply;
        boolean flag = true;
        int userID = -1;
        
        try{ 
            clientSocket.openCom();
            
            clientSocket.write(REGISTER + TOKEN + email + TOKEN +  username + TOKEN + password);
            
            inputLine=clientSocket.read();
            
            reply=decodeReply(inputLine, REGISTER);
            if(reply[0].equals("ok")){
                userID = Integer.parseInt(reply[1]);
                Authenticated.setClientSocket(clientSocket);
                flag = false;  
            }
            else if (reply[0].equals("duplicated"))
                userID = -2;
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        } finally{
            if (flag){
                try {
                    clientSocket.closeCom();
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to gnomo.");
                }
            }
        }
        
        return userID;
        
    }
    /**
     * Sets up a socket dedicated to requests from the server. 
     * <p>
     * This method opens and continuously reads from the socket, 
     * processing the server's request.
     * 
     */
    public static void startServerComs(){
        SocketClient serverSocket = new SocketClient();
        String inputLine;
        String[] reply;
        
        try{
            serverSocket.openCom();
            
            serverSocket.write(SOCKET + TOKEN + Authenticated.getID());
            inputLine = serverSocket.read();
            
            reply = decodeReply(inputLine, SOCKET);
            if(reply[0].equals("ok")){
                Authenticated.setServerSocket(serverSocket);
                ExecutorService executor = Executors.newCachedThreadPool();
                executor.submit(new Runnable(){
                    @Override
                    public void run() {
                        String inputLineInThread;
                        String replyInThread;
                        //System.out.println("server socket running");
                        while(true){
                            try{
                                if((inputLineInThread = Authenticated.getServerSocket().read()) != null) {
                                    replyInThread = Protocol.decodeServerRequests(inputLineInThread);
                                    if (replyInThread != null) {
                                        if (replyInThread.equals("exit")) {
                                            break;
                                        }
                                        
                                    }
                                }
                            } catch (IOException e) {
                                System.err.println("Couldn't get I/O for the connection to gnomo.");
                            }
                        }
                        //System.out.println("closing server socket");
                        try {
                            Authenticated.getServerSocket().closeCom();
                        } catch (IOException ex) {
                            System.err.println("Couldn't get I/O for the connection to gnomo.");
                        }
                        Authenticated.setServerSocket(null);
                        //System.out.println("server socket closed");
                        
                    }

                });
                executor.shutdown();
                
            }
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host gnomo.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
    }
    /**
     * Ends communication with the server.
     * <p>
     * This method tells the server it's shutting down communications 
     * and closes the socket.
     */
    public static void endComs(){
        String inputLine;
        
        try {
            Authenticated.getClientSocket().write(EXIT + TOKEN + Authenticated.getID());
            inputLine=Authenticated.getClientSocket().read();
            if(inputLine.equals(EXIT)){
                //System.out.println("closing client socket");
                Authenticated.getClientSocket().closeCom();
                //System.out.println("client socket closed");
            }
            
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        finally{
            Authenticated.setClientSocket(null);
        }
    }
    
    /**
     * Finds a random game.
     * 
     * @param userID    user identification number
     * @return          <code>true</code> if successful; 
     *                  <code>false</code> otherwise.
     */
    public static boolean findGame(int userID){
        
        String inputLine = null;
        String[] reply;
        
        /*Authenticated.getClientSocket().write(GAME + TOKEN + CREATE + TOKEN + userID);

        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(CREATE)){
                if(reply[1].equals("ok")){
                    Game.reset();
                    Game.setID(Integer.parseInt(reply[2]));
                    Game.setOpponent(reply[3]);
                    Game.setPlacingShips(true);
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
    
    /**
     * Places a ship on a specified location.
     * 
     * @param gameID        game identification number
     * @param userID        user identification number
     * @param size          boat size
     * @param rowStart      start position row
     * @param colStart      start position column
     * @param rowEnd        end position row
     * @param colEnd        end position column
     * @return              <code>true</code> if successful; 
     *                      <code>false</code> otherwise.
     */
    public static boolean placeShip(int gameID, int userID, int size, int rowStart, int colStart, int rowEnd, int colEnd){
        
        String inputLine;
        String[] reply;
        
        Authenticated.getClientSocket().write(GAME + TOKEN + PLACE + TOKEN + gameID + TOKEN + userID + TOKEN + size + TOKEN + rowStart + TOKEN + colStart + TOKEN + rowEnd + TOKEN + colEnd);
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
        
        return false;
        //return true;
    }
    /**
     * Starts the game.
     * <p>
     * This method tells the server the client is ready to play
     * and sets who's turn it is.
     * 
     * @param gameID    game identification number
     * @param userID    user identification number
     * @return          <code>true</code> if successful; 
     *                  <code>false</code> otherwise.
     */
    public static boolean beginGame(int gameID, int userID){
        
        String inputLine;
        String[] reply;
        
        Authenticated.getClientSocket().write(GAME + TOKEN + BEGIN + TOKEN + gameID + TOKEN + userID);

        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(BEGIN)){
                if(reply[1].equals(START)){
                    Game.setMyTurn(true);
                    return true;
                }
                else if(reply[1].equals(WAIT)){
                    Game.setMyTurn(false);
                    Game.setFirstTurn(true);
                    return true;
                }
                else
                    return false;
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        
        return false;/*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        Game.setMyTurn(true);
        return true;*/
    }
    /**
     * Attack the opponent.
     * <p>
     * This method sends an attack to the specified coordinates.
     * 
     * @param gameID    game identification number
     * @param userID    user identification number
     * @param row       attack row
     * @param col       attack column
     * @return          <code>true</code> if successful; 
     *                  <code>false</code> otherwise.
     */
    public static boolean fireShot(int gameID, int userID, int row, int col){
        
        String inputLine;
        String[] reply;
        
        Authenticated.getClientSocket().write(GAME + TOKEN + ATTACK + TOKEN + gameID + TOKEN + userID + TOKEN + row + TOKEN + col);
        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(ATTACK)){
                if(reply[1].equals("hit")){
                    Shot.setHit(true);
                    Shot.setCriticalHit(false);
                    return true;
                }
                else if(reply[1].equals("critical")){
                    Shot.setHit(true);
                    Shot.setCriticalHit(true);
                    return true;
                }
                else if(reply[1].equals("miss")){
                    Shot.setHit(false);
                    Shot.setCriticalHit(false);
                    return true;
                }
                else if(reply[1].equals(END)){
                    Shot.setFinalHit(true);
                    if(reply[2].equals("win")){
                        Game.setWin(true);
                        return true;
                    }
                    else if(reply[2].equals("lose")){
                        Game.setWin(false);
                        return true;
                    }
                    else
                        return false;
                }
                else{
                    return false;
                }
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        
        return false;
    }
    /**
     * Receive the opponent's attack.
     * <p>
     * This method marks the coordinates of the opponent's attack.
     * 
     * @param gameID    game identification number
     * @param userID    user identification number
     * @return          <code>true</code> if successful; 
     *                  <code>false</code> otherwise.
     */
    public static boolean receiveShot(int gameID, int userID){
        
        String inputLine;
        String[] reply;
        
        Authenticated.getClientSocket().write(GAME + TOKEN + ATTACK + TOKEN + gameID + TOKEN + userID + TOKEN + WAIT);
        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(ATTACK)){
                if(reply[1].equals("hit")){
                    Shot.mark(Integer.parseInt(reply[2]), Integer.parseInt(reply[3]));
                    Shot.setHit(true);
                    Shot.setCriticalHit(false);
                    return true;
                }
                else if(reply[1].equals("critical")){
                    Shot.mark(Integer.parseInt(reply[2]), Integer.parseInt(reply[3]));
                    Shot.setHit(true);
                    Shot.setCriticalHit(true);
                    return true;
                }
                else if(reply[1].equals("miss")){
                    Shot.mark(Integer.parseInt(reply[2]), Integer.parseInt(reply[3]));
                    Shot.setHit(false);
                    Shot.setCriticalHit(false);
                    return true;
                }
                else if(reply[1].equals(END)){
                    Shot.setFinalHit(true);
                    if(reply[2].equals("win")){
                        Game.setWin(true);
                        return true;
                    }
                    else if(reply[2].equals("lose")){
                        Game.setWin(false);
                        return true;
                    }
                    else
                        return false;
                }
                else{
                    return false;
                }
            }
            
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        
        return false;
    }
    /**
     * Surrender.
     * 
     * @param gameID    game identification number
     * @param userID    user identification number
     * @return          <code>true</code> if successful; 
     *                  <code>false</code> otherwise.
     */
    public static boolean concedeGame(int gameID, int userID){
        
        String inputLine;
        String[] reply;
        
        Authenticated.getClientSocket().write(GAME + TOKEN + QUIT + TOKEN + gameID + TOKEN + userID);

        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(QUIT)){
                if(reply[1].equals("ok")){
                    return true;
                }
                else
                    return false;
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        
        return false;
    }
    
    /**
     * Get online users list.
     * 
     * @param userID    user identification number
     * @return          array of user names;
     *                  <code>null</code> if no users online 
     *                  or error occurred.
     */
    public static String[] getChallengeList(int userID){
        String inputLine;
        String[] reply;
        
        Authenticated.getClientSocket().write(CHALLENGE + TOKEN + userID);

        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, CHALLENGE);
            if(reply[0].equals("error")){
                return null;
            }
            else{
                return reply;
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        
        return null;
    }
    
    /**
     * Challenge an opponent to a game.
     * 
     * @param userID            user identification number
     * @param opponentID        opponent identification number
     * @param opponentName      opponent name
     * @return                  "accept" if the opponent accepted;
     *                          "reject" if the opponent rejected;
     *                          "error" if an error occurred.
     */
    public static String challengeUser(int userID, int opponentID, String opponentName){
        
        String inputLine;
        String[] reply;
        if (userID > 0){
            Authenticated.getClientSocket().write(INVITE + TOKEN + userID + TOKEN + opponentID);
            try {
                inputLine = Authenticated.getClientSocket().read();
                reply = decodeReply(inputLine, INVITE);
                if(reply[0].equals("accept")){
                    Game.reset();
                    Game.setID(Integer.parseInt(reply[1]));
                    Game.setOpponent(opponentName);
                    Game.setPlacingShips(true);
                }
                return reply[0];
            } catch (IOException ex) {
                System.err.println("Couldn't get I/O for the connection to gnomo.");
            }
        }
        return "error";
    }
    
    /**
     * Answer an opponent's challenge request.
     * 
     * @param opponentID        opponent identification number
     * @param opponentName      opponent name
     * @return                  <code>true</code> if successful; 
     *                          <code>false</code> otherwise.
     */
    private static boolean receiveChallenge(int opponentID, String opponentName){
        
        String outputLine;
        String inputLine;
        String[] reply;
        
        outputLine = INVITE + TOKEN + "reply" + TOKEN + Authenticated.getID() + TOKEN + opponentID + TOKEN ;
        if(Authenticated.acceptedChallenge(opponentName)){
            outputLine += "accept";}
        else
            outputLine += "reject";
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            System.err.println("Thread interrupted");
        }
        
        Authenticated.getClientSocket().write(outputLine);
        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, INVITE);
            if(!reply[0].equals("error")){
                Game.reset();
                Game.setID(Integer.parseInt(reply[0]));
                Game.setOpponent(opponentName);
                Game.setPlacingShips(true);
                MainFrame.changeInterface(MainFrame.PLACESHIPS);
                return true;
            }
            else{
                return false;
            }
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        return false;
    }
    
    public static boolean sendChatMessage(int gameID, int userID, String message){
        String inputLine;
        String[] reply;
        
        Authenticated.getClientSocket().write(GAME + TOKEN + CHAT + TOKEN + gameID + TOKEN + userID + TOKEN + message);
        try {
            inputLine = Authenticated.getClientSocket().read();
            reply = decodeReply(inputLine, GAME);
            if(reply[0].equals(CHAT)){
                if (reply[1].equals("ok")){
                    return true;
                }
            }
            return false;
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to gnomo.");
        }
        return false;
    }
 
    
    /**
     * Interprets the reply message from the server.
     * <p>
     * This method receives the message reply from the server as well 
     * as the expected opcode. It then checks the validity of this reply 
     * and returns the important data.
     * 
     * @param input         message received from the server
     * @param operation     opcode to validate the message
     * @return              array of relevant data.
     */
    private static String[] decodeReply(String input, String operation){
        String opcode[] = input.split(TOKEN);
        if (opcode[0].equals(operation)){
            return Arrays.copyOfRange(opcode, 1, opcode.length);
        }
        else {
            System.err.println("Error communicating with server");
            return null;
        }
    }
     
    /**
     * Interprets the message from the server.
     * <p>
     * This method receives the message sent by server and calls
     * the appropriate methods to process the request.
     * 
     * @param message   message sent by the server
     * @return          message reporting success or not.
     */
    private static String decodeServerRequests(String message) {
        String[] opcode = message.split("#");
        String reply;
        switch (opcode[0]) {
            case EXIT:
                reply = EXIT;
                break;
                
            case INVITE:
                if (receiveChallenge(Integer.parseInt(opcode[1]), opcode[2]))
                    reply = "ok";
                else
                    reply = "error";
                break;
            case GAME:
                if (opcode[1].equals(CHAT) && opcode[2].equals("message")){
                    GameGUI.addToChat(opcode[3]);
                    reply = "ok";
                }
                else{
                    reply = "error";
                }
                break;

            default: 
                reply = "error";      
                break;
        }
        
    return reply;    
    }
    
}
