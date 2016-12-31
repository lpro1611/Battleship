package interfaces;

import businesslogicclient.Authenticated;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * Container for every different interface in this project.
 * @author Diogo Recharte
 */
public class MainFrame extends JFrame{
    public static final String LOGIN = "Login panel";
    public static final String REGISTER = "Register panel";
    public static final String HOME = "Home panel";
    public static final String SETTINGS = "Settings panel";
    public static final String LOADING = "Loading panel";
    public static final String PLACESHIPS = "Place Ships panel";
    public static final String GAME = "Game panel";
    public static final String CHALLENGE = "Challenge panel";
    public static JPanel mainPanel;
    private static Image backgroundImage = null;
    private static Clip clip;
    
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
        
        MainFrame.setBackgroundImage();
        
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new LoginGUI(), LOGIN);
        mainPanel.add(new RegisterGUI(), REGISTER);
        mainPanel.add(new HomePageGUI(), HOME);
        mainPanel.add(new SettingsGUI(), SETTINGS);
        mainPanel.add(new LoadingGUI(), LOADING);
        mainPanel.add(new PlaceShipsGUI(), PLACESHIPS);
        mainPanel.add(new GameGUI(), GAME);
        mainPanel.add(new ChallengeGUI(), CHALLENGE);
        this.setContentPane(mainPanel);
        //this.pack(); //poe a janela do tamanho preferido dos paineis
        
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e)
            {
                if (Authenticated.getID()>0)
                    Authenticated.logout();
                e.getWindow().dispose();
            }
        });
        
        
        
        this.setVisible(true);
    }
    
    public static void changeInterface(String gui){
        CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
        switch(gui){
            case LOGIN:
                cl.show(MainFrame.mainPanel, MainFrame.LOGIN);
                break;
            case REGISTER:
                cl.show(MainFrame.mainPanel, MainFrame.REGISTER);
                break;
            case HOME:
                cl.show(MainFrame.mainPanel, MainFrame.HOME);
                break;
            case SETTINGS:
                cl.show(MainFrame.mainPanel, MainFrame.SETTINGS);
                break;
            case LOADING:
                cl.show(MainFrame.mainPanel, MainFrame.LOADING);
                break;
            case PLACESHIPS:
                cl.show(MainFrame.mainPanel, MainFrame.PLACESHIPS);
                break;
            case GAME:
                cl.show(MainFrame.mainPanel, MainFrame.GAME);
                break;
            case CHALLENGE:
                cl.show(MainFrame.mainPanel, MainFrame.CHALLENGE);
                break;
            default:
                break;
        }
    }
    
    public static void music() {       
        AudioInputStream inputStream;
        try {
            inputStream = AudioSystem.getAudioInputStream(new File("src/resources/music/bensound-epic.wav"));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.err.println("Error playing sound file");
        }
    }
    
    public static void startMusic(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public static void stopMusic(){
        clip.stop();
    }
    
    public static Image getBackgroundImage(){
        return MainFrame.backgroundImage;
    }
    
    public static void setBackgroundImage(){
        try {
            MainFrame.backgroundImage = ImageIO.read(new File("src/resources/images/background.png"));
        } catch (IOException ex) {
            System.err.println("Error loading background image");
        }
    }
    
    
    /**
     * Creates the frame which will be the basis for all the interfaces.
     * 
     * @param args commands line arguments (not used)
     */
    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        music();
    }
}
