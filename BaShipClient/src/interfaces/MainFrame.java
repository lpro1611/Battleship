package interfaces;

import businesslogicclient.Authenticated;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 * Container for every different interface in this project.
 * @author Diogo Recharte
 */
public class MainFrame extends JFrame{
    static final String LOGIN = "Login panel";
    static final String REGISTER = "Register panel";
    static final String HOME = "Home panel";
    static final String SETTINGS = "Settings panel";
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
        mainPanel.add(new SettingsGUI(), SETTINGS);
        mainPanel.add(new LoadingGUI(), LOADING);
        mainPanel.add(new PlaceShipsGUI(), PLACESHIPS);
        mainPanel.add(new GameGUI(), GAME);
        this.setContentPane(mainPanel);
        //this.pack(); //poe a janela do tamanho preferido dos paineis
        
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowOpened(WindowEvent e) {
                // to do
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
    
  /*  public static void music() {       
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("./resources/music/bensound-epic.mp3");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }
*/
    
    
    /**
     * Creates the frame which will be the basis for all the interfaces.
     * 
     * @param args commands line arguments (not used)
     */
    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        //music();
    }
}
