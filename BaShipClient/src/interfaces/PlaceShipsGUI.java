package interfaces;

import businesslogicclient.Game;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/**
 *
 * @author diogo
 */
public class PlaceShipsGUI extends javax.swing.JPanel {

    /**
     * Creates new form PlaceShipsGUI
     */
    
    
    public PlaceShipsGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        board1 = new interfaces.BoardGUI();
        placeyourshipsLabel = new javax.swing.JLabel();
        adsPanel = new javax.swing.JPanel();
        adsLabel = new javax.swing.JLabel();
        shipsTable = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        carrierButton = new javax.swing.JButton();
        battleshipButton = new javax.swing.JButton();
        cruiserButton = new javax.swing.JButton();
        submarineButton = new javax.swing.JButton();
        destroyerButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        orientatioButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(220, 220, 225));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        board1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                board1MouseClicked(evt);
            }
        });

        placeyourshipsLabel.setBackground(new java.awt.Color(220, 220, 225));
        placeyourshipsLabel.setFont(new java.awt.Font("Tahoma", 1, 90)); // NOI18N
        placeyourshipsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        placeyourshipsLabel.setText("Place Your Ships");

        adsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        adsLabel.setText("Ads");

        javax.swing.GroupLayout adsPanelLayout = new javax.swing.GroupLayout(adsPanel);
        adsPanel.setLayout(adsPanelLayout);
        adsPanelLayout.setHorizontalGroup(
            adsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adsPanelLayout.createSequentialGroup()
                .addContainerGap(431, Short.MAX_VALUE)
                .addComponent(adsLabel)
                .addGap(431, 431, 431))
        );
        adsPanelLayout.setVerticalGroup(
            adsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adsPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(adsLabel)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("    Class      |      Ship      |     Size");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("   Carrier");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText(" Battleship");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("  Cruiser");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Submarine");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText(" Destroyer");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText(" 5 ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText(" 2 ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText(" 3 ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText(" 3 ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText(" 4 ");

        carrierButton.setBackground(new java.awt.Color(220, 220, 225));
        carrierButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/carrier.png"))); // NOI18N
        carrierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrierButtonActionPerformed(evt);
            }
        });

        battleshipButton.setBackground(new java.awt.Color(220, 220, 225));
        battleshipButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/battleship.png"))); // NOI18N
        battleshipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                battleshipButtonActionPerformed(evt);
            }
        });

        cruiserButton.setBackground(new java.awt.Color(220, 220, 225));
        cruiserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/cruiser.png"))); // NOI18N
        cruiserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cruiserButtonActionPerformed(evt);
            }
        });

        submarineButton.setBackground(new java.awt.Color(220, 220, 225));
        submarineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/submarine.png"))); // NOI18N
        submarineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submarineButtonActionPerformed(evt);
            }
        });

        destroyerButton.setBackground(new java.awt.Color(220, 220, 225));
        destroyerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/destroyer.png"))); // NOI18N
        destroyerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destroyerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout shipsTableLayout = new javax.swing.GroupLayout(shipsTable);
        shipsTable.setLayout(shipsTableLayout);
        shipsTableLayout.setHorizontalGroup(
            shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shipsTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(shipsTableLayout.createSequentialGroup()
                        .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(29, 29, 29)
                        .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carrierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(submarineButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(battleshipButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(cruiserButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(destroyerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        shipsTableLayout.setVerticalGroup(
            shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shipsTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shipsTableLayout.createSequentialGroup()
                        .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(carrierButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(battleshipButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(cruiserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(submarineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(shipsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(destroyerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(shipsTableLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        playButton.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        playButton.setText("Play");
        playButton.setEnabled(false);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        orientatioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/rotate.png"))); // NOI18N
        orientatioButton.setContentAreaFilled(false);
        orientatioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orientatioButtonActionPerformed(evt);
            }
        });

        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/undo.png"))); // NOI18N
        resetButton.setContentAreaFilled(false);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        settingsButton.setBackground(new java.awt.Color(220, 220, 225));
        settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/settings.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(adsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(shipsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(orientatioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addComponent(board1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(190, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(placeyourshipsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(placeyourshipsLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(settingsButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(board1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(shipsTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(playButton)
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(orientatioButton)
                        .addGap(146, 146, 146)))
                .addComponent(adsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        if(!board1.getBoard().placeAllShips()){
            board1.reset();
            carrierButton.setEnabled(true);
            battleshipButton.setEnabled(true);
            cruiserButton.setEnabled(true);
            submarineButton.setEnabled(true);
            destroyerButton.setEnabled(true);
            playButton.setEnabled(false);
            JOptionPane.showMessageDialog(PlaceShipsGUI.this, "Placing Ships Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
            cl.show(MainFrame.mainPanel, MainFrame.LOADING);
            ExecutorService executor = Executors.newCachedThreadPool();
            executor.submit(new Runnable(){
                @Override
                public void run() {
                    if(Game.begin()){
                        Game.setBoard(board1.getBoard());
                        CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
                        cl.show(MainFrame.mainPanel, MainFrame.GAME);
                    }
                    else{
                        CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
                        cl.show(MainFrame.mainPanel, MainFrame.HOME);
                        JOptionPane.showMessageDialog(PlaceShipsGUI.this, "Couldn't start game", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            
            });
            executor.shutdown();
        }
    }//GEN-LAST:event_playButtonActionPerformed

    private void carrierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrierButtonActionPerformed
        board1.setActionSize(5);
        carrierButton.setEnabled(false);
    }//GEN-LAST:event_carrierButtonActionPerformed

    private void battleshipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_battleshipButtonActionPerformed
        board1.setActionSize(4);
        battleshipButton.setEnabled(false);
    }//GEN-LAST:event_battleshipButtonActionPerformed

    private void cruiserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cruiserButtonActionPerformed
        board1.setActionSize(3);
        cruiserButton.setEnabled(false);
    }//GEN-LAST:event_cruiserButtonActionPerformed

    private void submarineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submarineButtonActionPerformed
        board1.setActionSize(3);
        submarineButton.setEnabled(false);
    }//GEN-LAST:event_submarineButtonActionPerformed

    private void destroyerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destroyerButtonActionPerformed
        board1.setActionSize(2);
        destroyerButton.setEnabled(false);
    }//GEN-LAST:event_destroyerButtonActionPerformed

    private void orientatioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orientatioButtonActionPerformed
        board1.toggleHorizontalOrientation();
    }//GEN-LAST:event_orientatioButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        board1.reset();
        carrierButton.setEnabled(true);
        battleshipButton.setEnabled(true);
        cruiserButton.setEnabled(true);
        submarineButton.setEnabled(true);
        destroyerButton.setEnabled(true);
        playButton.setEnabled(false);
    }//GEN-LAST:event_resetButtonActionPerformed

    private void board1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_board1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_board1MouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if (board1.boatsReady())
            playButton.setEnabled(true);
    }//GEN-LAST:event_formMouseMoved

    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Draw the background image.
    g.drawImage(MainFrame.getBackgroundImage(), 0, 0, this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adsLabel;
    private javax.swing.JPanel adsPanel;
    private javax.swing.JButton battleshipButton;
    private interfaces.BoardGUI board1;
    private javax.swing.JButton carrierButton;
    private javax.swing.JButton cruiserButton;
    private javax.swing.JButton destroyerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton orientatioButton;
    private javax.swing.JLabel placeyourshipsLabel;
    private javax.swing.JButton playButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JPanel shipsTable;
    private javax.swing.JButton submarineButton;
    // End of variables declaration//GEN-END:variables
}
