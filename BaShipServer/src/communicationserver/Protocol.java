package communicationserver;

import businesslogicserver.*;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.io.PrintWriter;
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
     * @param out
     * @return          message to reply to the client
     */
    public static String protocolDecode(String message, PrintWriter out) {
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
                    AuthenticatedUsers.addSocket(Integer.parseInt(opcode[1]), out);
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
    
    /**
     * This method decodes messages dedicated to challenging a player
     * <p>
     * If the message is a challenge the player identification numbers in the message
     * are given to the business logic to setup the challenge. If its a reply to the challenge,
     * the method passes the reply to the business logic. 
     * 
     * @param opcode message sent to the server
     * @return confirmation to Socket
     */
    private static String inviteDecode(String[] opcode) {
        String inviteAnswer;
        
        if (opcode[1].equals("reply")) {
            inviteAnswer = AuthenticatedUsers.replyChallenge(Integer.parseInt(opcode[3]), opcode[4]);
        } else {
            inviteAnswer = AuthenticatedUsers.setChallenge(Integer.parseInt(opcode[1]), Integer.parseInt(opcode[2]));
        }
        
        return inviteAnswer;
    }
    /**
     * This method decodes the messages dedicated to a match
     * <p>
     * This method analyses the received messages, and, based of its contents,
     * it chooses which method of the classes, involved in a match, to call.
     * 
     * @param opcode message received from Socket
     * @return confirmation to the Socket
     */
    private static String gameDecode(String[] opcode) {
        final String BEGIN = "begin";
        final String QUIT = "quit";
        final String CHAT = "chat";
        final String PLACE = "place";
        final String ATTACK = "attack";
        final String CREATE = "create";
        
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
                
            case QUIT:
                answer = QUIT + "#";
                try {
                    answer += Game.quitGame(Integer.parseInt(opcode[2]), Integer.parseInt(opcode[3]));
                } catch (Exception e) {
                    answer = "error";
                }
                break;
                
            case CREATE:
                answer = CREATE + "#";
                try {
                    answer += AuthenticatedUsers.playNow(Integer.parseInt(opcode[2]));
                } catch (Exception e) {
                    answer = "error";
                }
                break;
                
            case CHAT:
                answer = CHAT + "#";
                try {
                    answer += Game.internalChat(Integer.parseInt(opcode[2]), Integer.parseInt(opcode[3]), opcode[4]);
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
                    if (opcode[4].equals("wait")) {
                        answer += Game.oponentAttack(Integer.parseInt(opcode[2]), Integer.parseInt(opcode[3]));
                    } else {
                        answer += Game.attack(Integer.parseInt(opcode[2]), Integer.parseInt(opcode[3]), Integer.parseInt(opcode[4]), Integer.parseInt(opcode[5]));
                    }
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