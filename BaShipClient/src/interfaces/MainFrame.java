package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Container for every different interface in this project.
 * @author Diogo Recharte
 */
public class MainFrame extends JFrame{
    static final String LOGIN = "Login panel";
    static final String REGISTER = "Register panel";
    static final String HOME = "Home panel";
    static final String LOADING = "Loading panel";
    static final String PLACESHIPS = "Place Ships panel";
    static final String GAME = "Game panel";
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
        this.setSize(1280, 720);
        this.setResizable(false);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("BaShip");
        
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new LoginGUI(), LOGIN);
        mainPanel.add(new RegisterGUI(), REGISTER);
        mainPanel.add(new HomePageGUI(), HOME);
        mainPanel.add(new LoadingGUI(), LOADING);
        mainPanel.add(new PlaceShipsGUI(), PLACESHIPS);
        mainPanel.add(new GameGUI(), GAME);
        this.setContentPane(mainPanel);
        //this.pack(); //poe a janela do tamanho preferido dos paineis
        
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Opened");
            }
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
        
        
        
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
