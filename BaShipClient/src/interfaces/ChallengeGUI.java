package interfaces;

import businesslogicclient.Authenticated;
import businesslogicclient.Challenge;
import businesslogicclient.Game;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import javax.swing.text.Position;

/**
 *
 * @author diogo
 */
public class ChallengeGUI extends javax.swing.JPanel {

    /**
     * Creates new form HomePageGUI
     */
    public ChallengeGUI() {
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
        challengeButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        challengeScrollPane = new javax.swing.JScrollPane();
        challengeList = new javax.swing.JList<>();
        searchField = new javax.swing.JTextField();
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
        bashipLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/challenge-large.png"))); // NOI18N
        bashipLabel.setText(" Challenge");

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

        challengeButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        challengeButton.setText("Challenge");
        challengeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                challengeButtonActionPerformed(evt);
            }
        });

        settingsButton.setBackground(new java.awt.Color(220, 220, 225));
        settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/settings.png"))); // NOI18N
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        challengeList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        challengeList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        challengeList.setToolTipText("");
        challengeScrollPane.setViewportView(challengeList);

        searchField.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        searchField.setForeground(new java.awt.Color(153, 153, 153));
        searchField.setText(" Search");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFieldFocusGained(evt);
            }
        });
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(483, Short.MAX_VALUE)
                .addComponent(challengeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(482, 482, 482))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(adsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(challengeScrollPane)
                    .addComponent(searchField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bashipLabel)
                        .addGap(35, 35, 35)
                        .addComponent(challengeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(challengeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(adsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(settingsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(homeButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void challengeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_challengeButtonActionPerformed
        String username = challengeList.getSelectedValue();
        String reply;
        if( username != null){
            reply = Authenticated.challengeUser(username);
            switch (reply) {
                case "accept":
                    CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
                    cl.show(MainFrame.mainPanel, MainFrame.PLACESHIPS);
                    break;
                case "reject":
                    JOptionPane.showMessageDialog(ChallengeGUI.this, "User Rejected The Invite", "Denied", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(ChallengeGUI.this, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }//GEN-LAST:event_challengeButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
        cl.show(MainFrame.mainPanel, MainFrame.SETTINGS);
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        searchField.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        searchField.setForeground(new java.awt.Color(153, 153, 153));
        searchField.setText(" Search");
        challengeList.setListData(Challenge.getList());
    }//GEN-LAST:event_formComponentShown

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
        cl.show(MainFrame.mainPanel, MainFrame.HOME);
    }//GEN-LAST:event_homeButtonActionPerformed

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
        challengeList.setSelectedIndex(challengeList.getNextMatch(searchField.getText(), 0, Position.Bias.Forward));
    }//GEN-LAST:event_searchFieldKeyTyped

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        String username = challengeList.getSelectedValue();
        String reply;
        if( username != null){
            reply = Authenticated.challengeUser(username);
            switch (reply) {
                case "accept":
                    CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
                    cl.show(MainFrame.mainPanel, MainFrame.PLACESHIPS);
                    break;
                case "reject":
                    JOptionPane.showMessageDialog(ChallengeGUI.this, "User Rejected The Invite", "Denied", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(ChallengeGUI.this, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusGained
        searchField.setText("");
        searchField.setFont(new java.awt.Font("Tahoma", 0, 13));
        searchField.setForeground(Color.black);
    }//GEN-LAST:event_searchFieldFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adsLabel;
    private javax.swing.JPanel adsPanel;
    private javax.swing.JLabel bashipLabel;
    private javax.swing.JButton challengeButton;
    private javax.swing.JList<String> challengeList;
    private javax.swing.JScrollPane challengeScrollPane;
    private javax.swing.JButton homeButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton settingsButton;
    // End of variables declaration//GEN-END:variables
}