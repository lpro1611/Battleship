package interfaces;

import businesslogicclient.Authenticated;
import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author diogo
 */
public class HomePageGUI extends javax.swing.JPanel {

    /**
     * Creates new form HomePageGUI
     */
    public HomePageGUI() {
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
        playnowButton = new javax.swing.JButton();
        challengeButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(220, 220, 225));

        bashipLabel.setBackground(new java.awt.Color(220, 220, 225));
        bashipLabel.setFont(new java.awt.Font("Tahoma", 1, 90)); // NOI18N
        bashipLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bashipLabel.setText("BaShip");

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

        playnowButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        playnowButton.setText("Play Now");
        playnowButton.setOpaque(false);
        playnowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playnowButtonActionPerformed(evt);
            }
        });

        challengeButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        challengeButton.setText("Challenge");
        challengeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                challengeButtonActionPerformed(evt);
            }
        });

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
                        .addGap(483, 483, 483)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bashipLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playnowButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(challengeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bashipLabel)
                .addGap(99, 99, 99)
                .addComponent(playnowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(challengeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(adsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playnowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playnowButtonActionPerformed
        CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
        cl.show(MainFrame.mainPanel, MainFrame.LOADING);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Runnable(){
            @Override
            public void run() {
                String str=null;
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                try {
                    str=input.readLine();
                } catch (IOException ex) {
                }
                CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
                if(str.equals("game"))
                    cl.show(MainFrame.mainPanel, MainFrame.GAME);
                else
                    cl.show(MainFrame.mainPanel, MainFrame.PLACESHIPS);
            }
            
        });
        executor.shutdown();
    }//GEN-LAST:event_playnowButtonActionPerformed

    private void challengeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_challengeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_challengeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adsLabel;
    private javax.swing.JPanel adsPanel;
    private javax.swing.JLabel bashipLabel;
    private javax.swing.JButton challengeButton;
    private javax.swing.JButton playnowButton;
    // End of variables declaration//GEN-END:variables
}
