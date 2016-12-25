package communicationserver;

import businesslogicserver.*;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.SQLException;
import java.net.*;

/**
 * Implements the protocol used to communicate between the 
 * server and the client.
 * 
 * @author Afonso Ferreira
 */
public class Protocol {
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String SOCKET = "socket";
    private static final String EXIT = "exit";
    private static final String CHALLENGE = "challenge";
    private static final String INVITE = "invite";
    private static final String REPLAY = "replay";
    private static final String GAME = "game";
    
    private static String reply;
    
    /**
     * Class Constructor
     */
    public Protocol(){}
    
    /**
     * Receives the messages from the client and interprets it.
     * <p>
     * This method checks the contents of the received message and, 
     * using a series of opcodes, determines the correct business 
     * logic functionality to call. It then awaits the response of 
     * the business logic and replies to the client.
     * 
     * @param message   received message
     * @param socket    communication socket
     * @return          message to reply to the client
     */
    public static String protocolDecode(String message, Socket socket) {
        String opcode[] = message.split("#");
        
        switch (opcode[0]) {
            case LOGIN:   
                reply = LOGIN + "#";
                try {
                    int id = Login.verify(opcode[1], opcode[2]);
                    if (id == -1) {
                        reply += "duplicated";
                    } else {
                        reply += "ok#" + id;
                    }
                } catch (SQLException e) {
                    reply += "error";
                } catch (WrongPasswordException e) {
                    reply += "wrongpass";
                } catch (NotFoundException e) {
                    reply += "notfound";
                }
                break;
                            
            case REGISTER: 
                reply = REGISTER + "#";
                try {
                    int id = Login.register(opcode[1], opcode[2], opcode[3]);
                    reply += "ok#" + id;
                } catch (SQLException | NotFoundException e) {
                    reply += "error";
                } catch (DuplicatedNameException e) {
                    reply += "duplicated";
                }
                break;
                
            case SOCKET:
                try {
                    AuthenticatedUsers.addSocket(Integer.parseInt(opcode[1]), socket);
                    reply = "exit";
                } catch (NumberFormatException e) {
                    reply = SOCKET + "#error";
                }
                break;
                
            case EXIT:
                try {
                    AuthenticatedUsers.remove(Integer.parseInt(opcode[1]));
                    reply = "exit";
                } catch (NumberFormatException e) {
                    reply = EXIT + "#error";
                }
                
            case CHALLENGE:
                reply = CHALLENGE;
                try {
                    String list = AuthenticatedUsers.menuChallenge(Integer.parseInt(opcode[1]));
                    
                    if (list == null) {
                        reply += "#error";
                    } else {
                        // alreay has the '#'
                        reply += list;
                    }
                } catch (NumberFormatException e) {
                    reply += "#error";
                }
                
            case INVITE:
                reply = INVITE + "#";
                try {
                    String inviteAnswer = AuthenticatedUsers.setChallenge(Integer.parseInt(opcode[1]), Integer.parseInt(opcode[2]));
                    reply += inviteAnswer;
                } catch (Exception e) {
                    reply += "error";
                }
                
            default: 
                reply = "error";             
        }
        
    return reply;    
    }
}