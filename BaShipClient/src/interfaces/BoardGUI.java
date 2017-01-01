package interfaces;

import businesslogicclient.Board;
import businesslogicclient.Shot;
import java.awt.Color;
import javax.swing.*;
/**
 *
 * @author diogo
 */
public class BoardGUI extends JPanel{
    
    private Board board;
    private JButton[][] cell = new JButton[10][10];
    private int size=0;
    private boolean horizontalOrientation=true;
    
    public BoardGUI() {
        initComponents();
    }
    
    private void initComponents(){
        board = new Board();
        setBackground(new java.awt.Color(220, 220, 225));
        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setLayout(new java.awt.GridLayout(10, 10));
        
        for(int i=0; i<10; i++){
            for(int j=0;j<10;j++){
                cell[i][j] = new JButton();
                cell[i][j].putClientProperty( "row", i );
                cell[i][j].putClientProperty( "col", j );
                cell[i][j].setBackground(new java.awt.Color(220, 220, 225));
                //cell[i][j].setOpaque(false);
                //cell[i][j].setFocusable(false);
                cell[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cellActionPerformed(evt);
                    }
                });
                cell[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        cellMouseEntered(evt);
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        cellMouseExited(evt);
                    }
                });
                /*cell[i][j].addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        cellFocusGained(evt);
                    }
                });*/
                add(cell[i][j]);
            }
        }
        
        
    }
    private void cellActionPerformed(java.awt.event.ActionEvent evt) {
        if (size > 0){
            int row = (int)((JButton)evt.getSource()).getClientProperty( "row" );
            int col = (int)((JButton)evt.getSource()).getClientProperty( "col" );
            if (board.isValidPosition(row, col, size, horizontalOrientation)){
                if (size ==1){
                    Shot.mark(row, col);
                }

                board.placeTemporaryShip(row, col, size, horizontalOrientation);
                if (horizontalOrientation){
                    for (int i=col; i<col+size; i++){
                        if (i==10)
                            break;
                        cell[row][i].setBackground(new java.awt.Color(153,153,153));
                    }
                }
                else{
                    for (int i=row; i<row+size; i++){
                        if (i==10)
                            break;
                        cell[i][col].setBackground(new java.awt.Color(153,153,153));
                    }
                }
                size=0;
            }
        }
    }
    private void cellMouseEntered(java.awt.event.MouseEvent evt) {
        int row = (int)((JButton)evt.getSource()).getClientProperty( "row" );
        int col = (int)((JButton)evt.getSource()).getClientProperty( "col" );
        //System.out.println(board.isValidPosition(row, col, size, horizontalOrientation));
        if (board.isValidPosition(row, col, size, horizontalOrientation)){
            if (horizontalOrientation){
                for (int i=col; i<col+size; i++){
                    if (i==10)
                        break;
                    cell[row][i].setBackground(Color.green);
                }
            }
            else{
                for (int i=row; i<row+size; i++){
                    if (i==10)
                        break;
                    cell[i][col].setBackground(Color.green);
                }
            }
        }
        else{
            if (horizontalOrientation){
                for (int i=col; i<col+size; i++){
                    if (i==10)
                        break;
                    cell[row][i].setBackground(Color.red);
                }
            }
            else{
                for (int i=row; i<row+size; i++){
                    if (i==10)
                        break;
                    cell[i][col].setBackground(Color.red);
                }
            }
        }
    }
    private void cellMouseExited(java.awt.event.MouseEvent evt) {
        int row = (int)((JButton)evt.getSource()).getClientProperty( "row" );
        int col = (int)((JButton)evt.getSource()).getClientProperty( "col" );
        //cell[row][col].setBackground(UIManager.getColor("control"));
        if (horizontalOrientation)
                for (int i=col; i<col+size; i++){
                    if (i==10)
                        break;
                    if (board.getState(row, i)==1)
                        cell[row][i].setBackground(new java.awt.Color(153, 153, 153));
                    else if(board.getState(row, i)==2)
                        cell[row][i].setBackground(Color.red);
                    else if(board.getState(row, i)==3)
                        cell[row][i].setBackground(Color.blue);
                    else
                        cell[row][i].setBackground(new java.awt.Color(220, 220, 225));
                }
            else
                for (int i=row; i<row+size; i++){
                    if (i==10)
                        break;
                    if (board.getState(i, col)==1)
                        cell[i][col].setBackground(new java.awt.Color(153, 153, 153));
                    else if(board.getState(row, i)==2)
                        cell[row][i].setBackground(Color.red);
                    else if(board.getState(row, i)==3)
                        cell[row][i].setBackground(Color.blue);
                    else
                        cell[i][col].setBackground(new java.awt.Color(220, 220, 225));
                }
    }
    
    public void setActionSize(int size){
        this.size=size;
    }
    public boolean boatsReady(){
        return board.shipsReady();
    }
    
    public void showShot(){
        board.markShot();
        int row = Shot.getRow();
        int col = Shot.getCol();
        if(Shot.isHit())
            cell[row][col].setBackground(Color.red);
        else
            cell[row][col].setBackground(Color.blue);
    }
    public void toggleHorizontalOrientation(){
        this.horizontalOrientation = (!this.horizontalOrientation);
    }
    
    public void setBoard(Board board){
        this.board = board;
    }
    
    public void updateBoard(){
        for(int i=0; i<10; i++){
            for(int j=0;j<10;j++){
                if (board.getState(i, j)==1){
                    cell[i][j].setBackground(new java.awt.Color(153, 153, 153));
                }
            }
        }
    }
    public void reset(){
        board.reset();
        for(int row = 0; row<10; row++)
            for(int col = 0; col<10; col++)
                cell[row][col].setBackground(new java.awt.Color(220, 220, 225));
    }
    public Board getBoard(){
        return board;
    }
}
