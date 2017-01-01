package businesslogicclient;

import communicationclient.Protocol;
import communicationclient.SocketClient;
import interfaces.InvitePopup;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
     * @param id            authenticated's unique identifier number
     * @param username      authenticated's name
     */
    public Authenticated(int id, String username){
        Authenticated.id = id;
        Authenticated.username = username;
    }
    
    /**
     * Returns the authenticated user's id number.
     * <p>
     * This method always returns successfully.
     * 
     * @return  authenticated user's unique identifier number
     */
    public static int getID(){
        return id;
    }
    
    /**
     * Returns the authenticated user's name.
     * <p>
     * This method always returns successfully.
     * 
     * @return  authenticated user's name
     */
    public static String getUsername(){
        return username;
    }
    
    public static SocketClient getClientSocket(){
        return clientSocket;
    }
    public static SocketClient getServerSocket(){
        return serverSocket;
    }
    
    /**
     * Sets the authenticated user's id number.
     *
     * @param id    authenticated user's unique identifier number
     */
    public static void setID(int id){
        Authenticated.id = id;
    }
    
    /**
     * Sets the authenticated user's name.
     * 
     * @param username  authenticated user's name
     */
    public static void setUsername(String username){
        Authenticated.username = username;
    }
    
    public static void setClientSocket(SocketClient socket){
        Authenticated.clientSocket = socket;
    }
     public static void setServerSocket(SocketClient socket){
        Authenticated.serverSocket = socket;
    }
     
    public static void logout(){
        /*if (Game.isRunning()){
            Game.concede();
        }*/
        Protocol.endComs();
        Authenticated.setID(0);
        Authenticated.setUsername(null);
    }
    
    public static boolean playGame(){
        return Protocol.findGame(Authenticated.id);
    }

    public static String challengeUser(String username){
        return Protocol.challengeUser(id, Challenge.getID(username), username);
    }
    
    public static boolean acceptedChallenge (String username){
        Challenge.accept(false);
        if(!Game.isRunning()){
            
            //boolean done;
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
                //done = invitePanel.isDone();
                //System.out.println(done);
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
