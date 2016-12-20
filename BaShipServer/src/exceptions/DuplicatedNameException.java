package exceptions;

/**
 * Triggered when a visitor tries to create an account with a name
 * that already exists.
 * 
 * @author diogo
 */
public class DuplicatedNameException extends Exception {
    
    /**
     * Class Constructor
     */
    public DuplicatedNameException(){}
    
}
