package interfaces;

import businesslogicclient.Board;
import businesslogicclient.Shot;
import java.awt.Color;
import javax.swing.*;
/**
 * Represents the board graphical user interface.
 * @author Diogo Recharte
 */
public class BoardGUI extends JPanel{
    
    private Board board;
    private JButton[][] cell = new JButton[10][10];
    private int size=0;
    private boolean horizontalOrientation=true;
    
    /**
     * Class Constructor.
     * <p>
     * Initialises all the components used in the Board GUI.
     */
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
    
    /**
     * Sets the size of the action being performed.
     * 
     * @param size      size of the action
     */
    public void setActionSize(int size){
        this.size=size;
    }
    
    /**
     * Returns if all ships are placed or not.
     * 
     * @return      <code>true</code> if all ships are placed; 
     *              <code>false</code> otherwise.
     */
    public boolean shipsReady(){
        return board.shipsReady();
    }
    
    /**
     * Update the interface with the attack.
     * 
     */
    public void showShot(){
        board.markShot();
        int row = Shot.getRow();
        int col = Shot.getCol();
        if(Shot.isHit())
            cell[row][col].setBackground(Color.red);
        else
            cell[row][col].setBackground(Color.blue);
    }
    
    /**
     * Toggles ship's orientation.
     * <p>
     * Vertical or horizontal.
     */
    public void toggleHorizontalOrientation(){
        this.horizontalOrientation = (!this.horizontalOrientation);
    }
    
    /**
     * Sets the board.
     * 
     * @param board     board to set
     */
    public void setBoard(Board board){
        this.board = board;
    }
    
    /**
     * Updates the interface with the board data.
     * 
     */
    public void updateBoard(){
        for(int i=0; i<10; i++){
            for(int j=0;j<10;j++){
                if (board.getState(i, j)==1){
                    cell[i][j].setBackground(new java.awt.Color(153, 153, 153));
                }
            }
        }
    }
    
    /**
     * Resets the interface.
     * 
     */
    public void reset(){
        board.reset();
        for(int row = 0; row<10; row++)
            for(int col = 0; col<10; col++)
                cell[row][col].setBackground(new java.awt.Color(220, 220, 225));
    }
    /**
     * Returns the board.
     * 
     * @return  board.
     */
    public Board getBoard(){
        return board;
    }
}
