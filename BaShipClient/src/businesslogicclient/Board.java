package businesslogicclient;

/**
 *
 * @author diogo
 */
public class Board {
    
    private final int[][]cell;
    private int numCellsFilled;
    
    public Board(){
        cell = new int[10][10];
        numCellsFilled = 0;
    }
    
    public boolean isValidPosition(int row, int col, int size, boolean horizontalOrientation){
        if (horizontalOrientation) {
            if (col > 10-size)
                return false;
            else{
                for (int i=col; i<col+size; i++)
                    if (cell[row][i]!=0)
                        return false;
            }
        }
        else{
            if (row > 10-size)
                return false;
            else{
                for (int i=row; i<row+size; i++)
                    if (cell[i][col]!=0)
                        return false;
            }
        }
        return true;
    }
    
    public void placeShip(int row, int col, int size, boolean horizontalOrientation){
        if (horizontalOrientation) {
            for (int i=col; i<col+size; i++){
                cell[row][i]=1;
            }
        }
        else{
            for (int i=row; i<row+size; i++)
                cell[i][col]=1;
        }
        numCellsFilled += size;
    }
    
    public int getState(int row, int col){
        return cell[row][col];
    }
    
    public boolean boatsReady(){
        if (numCellsFilled == 17)
            return true;
        else 
            return false;
    }
    
    public void markShot(){
        int row = Shot.getRow();
        int col = Shot.getCol();
        if(Shot.isHit())
            cell[row][col] = 2;
        else
            cell[row][col] = 3;
    }
    
    public void printBoard(){
        for (int i=0; i<10; i++){
            for (int j=0; j<10;j++)
                System.out.print(cell[i][j]+ " ");
            System.out.println("");
        }
        System.out.println("");
    }
    public void reset(){
        for(int i=0; i<10; i++)
            for(int j=0; j<10;j++)
                cell[i][j]=0;
        numCellsFilled = 0;
    }
    
}
