package businesslogicserver;

import dataacess.DbUser;
import exceptions.NotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * This class hold the data server-side
 * of a  currently logged player
 * @author diogo
 */
public class Authenticated {
    private final int id;
    private final String name;
    private PrintWriter out;
    private int currentGameId;
   
    /**
     * Construtor of the class, receives the identification
     * numebr o player. Uses it to get the username from the
     * database and initializes the currentGameId 
     * 
     * @param id player identification number
     * @throws SQLException Exception in case of database issues
     * @throws NotFoundException Exception in case usernam not found
     */
    public Authenticated(int id) throws SQLException, NotFoundException {
        this.id = id;
        name = DbUser.getUserNameById(id);
        currentGameId = -1;
    }
    
    /**
     * Gets identification number
     * @return  identification number
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets username
     * @return  username
     */
    public String getName() {
        return name;
    }
    /**
     * Gets current game identification number
     * @return  current game identification number
     */
    public int getCurrentGameId() {
        return currentGameId;
    }
   
    /**
     * Gets the output stream associated with the user
     * @return  output stream associated wit the user
     */
    public PrintWriter getSocket() {
        return out;
    }
    
    /**
     * Sets the currentGameId as the one passed in the
     * argument of the method
     * 
     * @param currentGameId identification number of game to give the user 
     */
    public void setCurrentGameId(int currentGameId) {
        if (currentGameId > -1) {
            this.currentGameId = currentGameId;
        }
    }
    
    /**
     * clear the currentGameid
     */
    public void clearCurrentGameId() {
        currentGameId = -1;
    }
    
    /**
     * This method gives a user an output stream, 
     * associated with a socket on the server side, 
     * so that the server can send messages to this user.
     * 
     * 
     * @param out Output stream that is given to the user 
     */
    public void setSocket(PrintWriter out) {
        this.out = out;
    }
}
