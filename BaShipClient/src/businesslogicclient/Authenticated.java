package businesslogicclient;

import communicationclient.Protocol;
import communicationclient.SocketClient;
import interfaces.InvitePopup;
import javax.swing.JFrame;

/**
 * Represents an authenticated user which has a name 
 * and a unique number identifier.
 * @author Diogo Recharte
 */
public class Authenticated {
    private static int id;
    private static String username;
    private static SocketClient clientSocket;
    private static SocketClient serverSocket;
    
    /**
     * Class constructor specifying id number and username of
     * the authenticated user.
     * 
     * @param id            authenticated user identification number
     * @param username      authenticated user name
     */
    public Authenticated(int id, String username){
        Authenticated.id = id;
        Authenticated.username = username;
    }
    
    /**
     * Returns the authenticated user id number.
     * <p>
     * This method always returns successfully.
     * 
     * @return  authenticated user unique identifier number.
     */
    public static int getID(){
        return id;
    }
    
    /**
     * Returns the authenticated user name.
     * <p>
     * This method always returns successfully.
     * 
     * @return  authenticated user name.
     */
    public static String getUsername(){
        return username;
    }
    
    /**
     * Returns the authenticated user socket for client communication.
     * <p>
     * This method always returns successfully.
     * 
     * @return  authenticated user client communication socket.
     */
    public static SocketClient getClientSocket(){
        return clientSocket;
    }
    
    /**
     * Returns the authenticated user socket for server communication.
     * <p>
     * This method always returns successfully.
     * 
     * @return  authenticated user server communication socket.
     */
    public static SocketClient getServerSocket(){
        return serverSocket;
    }
    
    /**
     * Sets the authenticated user id number.
     *
     * @param id    authenticated user identification number
     */
    public static void setID(int id){
        Authenticated.id = id;
    }
    
    /**
     * Sets the authenticated user name.
     * 
     * @param username  authenticated user name
     */
    public static void setUsername(String username){
        Authenticated.username = username;
    }
    
    /**
     * Sets the authenticated user socket for client communication.
     * 
     * @param socket    socket for client communication
     */
    public static void setClientSocket(SocketClient socket){
        Authenticated.clientSocket = socket;
    }
    
    /**
     * Sets the authenticated user socket for server communication.
     * 
     * @param socket    socket for server communication
     */
     public static void setServerSocket(SocketClient socket){
        Authenticated.serverSocket = socket;
    }
     
    /**
     * Logs out the authenticated user.
     * <p>
     * Calls the communication protocol to access the server and 
     * log out the user.
     * 
     */
    public static void logout(){
        /*if (Game.isRunning()){
            Game.concede();
        }*/
        Protocol.endComs();
        Authenticated.setID(0);
        Authenticated.setUsername(null);
    }
    
    /**
     * Finds a random game.
     * <p>
     * Calls the communication protocol to access the server and 
     * find a random game.
     * 
     * @return      <code>true</code> if successful; 
     *              <code>false</code> otherwise.
     */
    public static boolean playGame(){
        return Protocol.findGame(Authenticated.id);
    }

    /**
     * Challenge an opponent to a game.
     * <p>
     * Calls the communication protocol to access the server and 
     * challenge an opponent to a game.
     * 
     * @param username      opponent name
     * @return              "accept" if the opponent accepted;
     *                      "reject" if the opponent rejected;
     *                      "error" if an error occurred.
     */
    public static String challengeUser(String username){
        return Protocol.challengeUser(id, Challenge.getID(username), username);
    }
    
    /**
     * Shows the authenticated the received challenge and asks
     * if he wishes to accept or decline the invite.
     * <p>
     * This method creates a pop-up window asking the user if he
     * wishes to accept or decline the invite.
     * 
     * @param username      opponent name
     * @return              <code>true</code> if accepted the invite; 
     *                      <code>false</code> otherwise.
     */
    public static boolean acceptedChallenge (String username){
        Challenge.accept(false);
        if(!Game.isRunning()){
            
            JFrame inviteFrame = new JFrame("Invite");
            InvitePopup invitePanel = new InvitePopup();
            invitePanel.setUsername(username);
            inviteFrame.setSize(400, 130);
            inviteFrame.setResizable(false);
            inviteFrame.setLocationRelativeTo(null);
            inviteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            inviteFrame.add(invitePanel);

            inviteFrame.setVisible(true);


            while(true){
                if(invitePanel.isDone())
                    break;
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    
                }
            }
            inviteFrame.dispose();
            
        }     
        
        return Challenge.wasAccepted();
    }
}
