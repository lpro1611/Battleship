package interfaces;

import businesslogicclient.Game;
import businesslogicclient.Shot;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
/**
 *
 * @author diogo
 */
public class GameGUI extends javax.swing.JPanel {

    /**
     * Creates new form PlaceShipsGUI
     */
    
    
    public GameGUI() {
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
        adsPanel = new javax.swing.JPanel();
        adsLabel = new javax.swing.JLabel();
        attackButton = new javax.swing.JButton();
        board2 = new interfaces.BoardGUI();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        youLabel = new javax.swing.JLabel();
        opponentLabel = new javax.swing.JLabel();
        turnLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(220, 220, 225));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        board1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                board1MouseClicked(evt);
            }
        });

        adsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        adsLabel.setText("Ads");

        javax.swing.GroupLayout adsPanelLayout = new javax.swing.GroupLayout(adsPanel);
        adsPanel.setLayout(adsPanelLayout);
        adsPanelLayout.setHorizontalGroup(
            adsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adsPanelLayout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addComponent(adsLabel)
                .addGap(206, 206, 206))
        );
        adsPanelLayout.setVerticalGroup(
            adsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adsPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(adsLabel)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        attackButton.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        attackButton.setText("Attack");
        attackButton.setEnabled(false);
        attackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackButtonActionPerformed(evt);
            }
        });

        board2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                board2MouseClicked(evt);
            }
        });

        jTextField1.setText("Type here");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(2, 445));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        youLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        youLabel.setText("You");

        opponentLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        opponentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        turnLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(board1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(youLabel)
                                .addGap(199, 199, 199)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(board2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opponentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addComponent(attackButton)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(adsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(turnLabel)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(youLabel)
                        .addGap(18, 18, 18)
                        .addComponent(board1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(opponentLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(board2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(attackButton))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(turnLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(adsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void attackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackButtonActionPerformed
        if(Shot.fire()){
            board2.showShot();
            //mudar o turno
            //esperar pelo adversario para atualizar o board1
        }
        else{
            CardLayout cl = (CardLayout)(MainFrame.mainPanel.getLayout());
            cl.show(MainFrame.mainPanel, MainFrame.HOME);
            JOptionPane.showMessageDialog(GameGUI.this, "An error occured", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_attackButtonActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if (Game.isMyTurn()){           //change the turns to other method
            if(Shot.isMarked()){
                board2.setActionSize(0);
                attackButton.setEnabled(true);
            }
            else{
                board2.setActionSize(1);
                attackButton.setEnabled(false);
            }
        }
        else{
            board2.setActionSize(0);
            attackButton.setEnabled(false);
        }
    }//GEN-LAST:event_formMouseMoved

    private void board1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_board1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_board1MouseClicked

    private void board2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_board2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_board2MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        board1.setBoard(Game.getBoard1());
        board1.updateBoard();
        opponentLabel.setText(Game.getOpponent());
        if (Game.isMyTurn()){
            turnLabel.setText("Your Turn");
            board2.setActionSize(1);
            attackButton.setEnabled(false);
        }
        else{
            turnLabel.setText("Your Opponent's Turn");
            board2.setActionSize(0);
            attackButton.setEnabled(false);
            //wait for the opponents move
        }
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adsLabel;
    private javax.swing.JPanel adsPanel;
    private javax.swing.JButton attackButton;
    private interfaces.BoardGUI board1;
    private interfaces.BoardGUI board2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel opponentLabel;
    private javax.swing.JLabel turnLabel;
    private javax.swing.JLabel youLabel;
    // End of variables declaration//GEN-END:variables
}
