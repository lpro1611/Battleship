/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicationserver;
import businesslogicserver.*;
import javafx.util.Pair;
/**
 *
 * @author Alunos-i221-16
 */
public class Protocol {
    private final String OPCODE1 = "login";
    
    public Protocol() {}
    
    public void ProtocolDecode(String a) {
        String opcode[] = a.split("#");
        
        switch (opcode[0]) {
            case OPCODE1:   Pair<String,String> login = new Pair<>(opcode[1],opcode[2]);
                            VerifyLogin(login);
                            break;
            
            default: System.out.println("treta");             
        }
    }
    
    // redundância
    public void VerifyLogin(Pair<String,String> login) {
        Login log = new Login(login);
        log.VerifyLogin();
    }
}