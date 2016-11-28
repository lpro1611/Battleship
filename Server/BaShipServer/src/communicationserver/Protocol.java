/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationserver;
import businesslogicserver.*;
import exceptions.DuplicatedNameException;
import exceptions.NotFoundException;
import exceptions.WrongPasswordException;
import java.sql.SQLException;
import java.util.Properties;
import javafx.util.Pair;
/**
 *Class that implements the protocol between the server and the cliente on the server side.
 * @author Alunos-i221-16
 */
public class Protocol {
    private final String OPCODE1 = "login";
    private final String OPCODE2 = "register";
    
    private String reply;
    /**
     *Receives the messages from the client ,and interprets it acording to the protocol.
     * <p>
     * This method checks the contents of the received message, and using a series of
     * opcodes it determines the correct bussiness logic functionality to call. 
     * It then awaits the response of the bussiness logig, and send it to the client.
     * <p>
     * @param a String that holds the recieved message
     * @param props Properties of the communication
     * @return  a String holding the reply to the client
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