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
 * @author Alunos-i221-16
 */
public class Protocol {
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String SOCKET = "socket";
    private static final String EXIT = "exit";
    private static final String REPLAY = "replay";
    private static final String CHALLENGE = "challenge";
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
     * @param a         received message
     * @return          message to reply to the client
     */
    public static String protocolDecode(String a, Socket socket) {
        String opcode[] = a.split("#");
        
        switch (opcode[0]) {
            case LOGIN:   
                reply = LOGIN + "#";
                try {
                    int id = Login.verify(opcode[1], opcode[2]);
                    reply += "ok#" + id;
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
                
                break;
                
            default: 
                reply = "error";             
        }
        
    return reply;    
    }
}