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
        String[] opcode = message.split("#");
        
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
                break;
                
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
                break;
                
            case INVITE:
                reply = INVITE + "#";
                try {
                    reply += inviteDecode(opcode);
                } catch (Exception e) {
                    reply += "error";
                }
                break;
                
            case GAME:
                reply = GAME + "#";
                try {
                    reply += gameDecode(opcode);
                } catch (Exception e) {
                    reply += "error";
                }
                break;
                
            default: 
                reply = "error";             
        }
        
    return reply;    
    }
    
    private static String inviteDecode(String[] opcode) {
        String inviteAnswer;
        
        if (!opcode[1].equals("reply")) {
            inviteAnswer = AuthenticatedUsers.setChallenge(Integer.parseInt(opcode[1]), Integer.parseInt(opcode[2]));
        } else {
            inviteAnswer = AuthenticatedUsers.replyChallenge(Integer.parseInt(opcode[3]), opcode[4]);
        }
        
        return inviteAnswer;
    }
    
    private static String gameDecode(String[] opcode) {
        final String BEGIN = "begin";
        final String END = "end";
        final String QUIT = "quit";
        final String CHAT = "chat";
        final String PLACE = "place";
        final String ATTACK = "attack";
        
        String answer = "error";
        
        switch (opcode[1]) {
            case BEGIN:
                answer = BEGIN + "#";
                try {
                    answer += Game.playerReady(Integer.parseInt(opcode[2]), Integer.parseInt(opcode[3]));
                } catch (Exception e) {
                    answer = "error";
                }
                break;
                
            case END:
                answer = END + "#";
                try {
                    
                } catch (Exception e) {
                    answer = "error";
                }
                break;
                
            case QUIT:
                answer = QUIT + "#";
                try {
                    
                } catch (Exception e) {
                    answer = "error";
                }
                break;
                
            case CHAT:
                answer = CHAT + "#";
                try {
                    
                } catch (Exception e) {
                    answer = "error";
                }
                break;
                
            case PLACE:
                answer = PLACE + "#";
                try {
                    answer += Game.placeShips(Integer.parseInt(opcode[2]), Integer.parseInt(opcode[3]), Integer.parseInt(opcode[4]), Integer.parseInt(opcode[5]), Integer.parseInt(opcode[6]), Integer.parseInt(opcode[7]), Integer.parseInt(opcode[8]));
                } catch (Exception e) {
                    answer = "error";
                }
                break;
                
            case ATTACK:
                answer = ATTACK + "#";
                try {
                    
                } catch (Exception e) {
                    answer = "error";
                }
                break;
                
            default:
                //do nothing
        }
        
        return answer;
    }
}