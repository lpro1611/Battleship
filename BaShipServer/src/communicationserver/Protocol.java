package communicationserver;

import businesslogicserver.*;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Implements the protocol used to communicate between the 
 * server and the client.
 * @author Alunos-i221-16
 */
public class Protocol {
    private final String OPCODE1 = "login";
    private final String OPCODE2 = "register";
    
    private String reply;
    
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
     * @param props     DB's connection properties
     * @return          message to reply to the client
     */
    public String ProtocolDecode(String a, Properties props) {
        String opcode[] = a.split("#");
        
        switch (opcode[0]) {
            case OPCODE1:   
                reply = OPCODE1 + "#";
                try {
                    int id = Login.verify(opcode[1], opcode[2], props);
                    reply += "ok#" + id;
                } catch (SQLException e) {
                    reply += "error";
                } catch (WrongPasswordException e) {
                    reply += "wrongpass";
                } catch (NotFoundException e) {
                    reply += "notfound";
                }
                break;
                            
            case OPCODE2: 
                reply = OPCODE2 + "#";
                try {
                    int id = Login.register(opcode[1], opcode[2], opcode[3], props);
                    reply += "ok#" + id;
                } catch (SQLException | NotFoundException e) {
                    reply += "error";
                } catch (DuplicatedNameException e) {
                    reply += "duplicated";
                }
                break;
                
            default: 
                reply = "error";             
        }
        
    return reply;    
    }
}