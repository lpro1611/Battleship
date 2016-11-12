/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author diogo
 */
public class MainFrame extends JFrame{
    static MainFrame frame1;
    static final String FIRST = "First panel";
    static final String SECOND = "Second panel";
    static JPanel mainPanel;
    
    public MainFrame(){
        
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
    
    public static void main(String[] args){
        frame1 = new MainFrame();
    }
}
