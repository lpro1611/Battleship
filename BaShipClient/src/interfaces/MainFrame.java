package interfaces;

import javax.swing.*;
import java.awt.*;

/**
 * Container for every different interface in this project.
 * @author Diogo Recharte
 */
public class MainFrame extends JFrame{
    static final String FIRST = "First panel";
    static final String SECOND = "Second panel";
    static JPanel mainPanel;
    /**
     * Constructor for the class.
     * <p>
     * Initialises all the components needed to manage the 
     * different interfaces.
     */
    public MainFrame(){
        
        initComponents();
    }
    private void initComponents(){
        this.setSize(1028, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("BaShip");
        
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new LoginGUI(), FIRST);
        mainPanel.add(new RegisterGUI(), SECOND);
        this.setContentPane(mainPanel);
        //this.pack(); //poe a janela do tamanho preferido dos paineis
        
        this.setVisible(true);
    }
    /**
     * Creates the frame which will be the basis for all the interfaces.
     * 
     * @param args commands line arguments (not used)
     */
    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
    }
}
