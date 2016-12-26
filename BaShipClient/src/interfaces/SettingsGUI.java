package interfaces;

import businesslogicclient.Authenticated;
import businesslogicclient.Game;
import java.awt.CardLayout;
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
public class SettingsGUI extends javax.swing.JPanel {

    /**
     * Creates new form HomePageGUI
     */
    public SettingsGUI() {
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

        bashipLabel = new javax.swing.JLabel();
        adsPanel = new javax.swing.JPanel();
        adsLabel = new javax.swing.JLabel();
        lougoutButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(220, 220, 225));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        bashipLabel.setBackground(new java.awt.Color(220, 220, 225));
        bashipLabel.setFont(new java.awt.Font("Tahoma", 0, 90)); // NOI18N
        bashipLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bashipLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/settings-large.png"))); // NOI18N
        bashipLabel.setText(" Settings");

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

        lougoutButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lougoutButton.setText("Logout");
        lougoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lougoutButtonActionPerformed(evt);
            }
        });

        homeButton.setBackground(new java.awt.Color(220, 220, 225));
        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/home.png"))); // NOI18N
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(bashipLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(adsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(483, 483, 483)
                        .addComponent(lougoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bashipLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(homeButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                .addComponent(lougoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(adsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lougoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lougoutButtonActionPerformed
        if (Game.isRunning()){
            if (Game.concede()){
                JOptionPane.showMessageDialog(SettingsGUI.this, "You Lost", "Game Over", JOptionPane.CANCEL_OPTION);
                CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
                cl.show(MainFrame.mainPanel, MainFrame.HOME);
            }
            else{
                JOptionPane.showMessageDialog(SettingsGUI.this, "An error occured", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            if (Authenticated.getID()>=0)
                Authenticated.logout();
            CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
            cl.show(MainFrame.mainPanel, MainFrame.LOGIN);
        }
    }//GEN-LAST:event_lougoutButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        if (Game.isRunning()){
            CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
            cl.show(MainFrame.mainPanel, MainFrame.GAME);
        }
        else{
            CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
            cl.show(MainFrame.mainPanel, MainFrame.HOME);
        }
    }//GEN-LAST:event_homeButtonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        if (Game.isRunning()){
            lougoutButton.setText("Concede Game");
            homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/back.png")));
        }
        else{
            lougoutButton.setText("Logout");
            homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/home.png")));
        }
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adsLabel;
    private javax.swing.JPanel adsPanel;
    private javax.swing.JLabel bashipLabel;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton lougoutButton;
    // End of variables declaration//GEN-END:variables
}
