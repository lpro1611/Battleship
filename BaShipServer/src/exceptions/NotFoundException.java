package exceptions;

/**
 * Triggered when a visitor tries to login with a name 
 * that doesn't exist in the Data Base.
 * 
 * @author diogo
 */
public class NotFoundException extends Exception {
    
    /**
     * Class Constructor
     */
    public NotFoundException(){}
    
}
