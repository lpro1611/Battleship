package businesslogicclient;

import communicationclient.Protocol;
import java.util.ArrayList;

/**
 *
 * @author diogo
 */
public class Board {
    
    private final int[][]cell;
    private int numCellsFilled;
    private final ArrayList<BoatPlacement> boatsPlacement;
    
    public Board(){
        cell = new int[10][10];
        numCellsFilled = 0;
        boatsPlacement = new ArrayList<>();
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
    
    public void placeTemporaryShip(int row, int col, int size, boolean horizontalOrientation){
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
        boatsPlacement.add(new BoatPlacement(row, col, size, horizontalOrientation));
    }
    
    public boolean placeAllShips(){
       if(boatsPlacement.size()==5){
           for(BoatPlacement boat : boatsPlacement){
               if(!Protocol.placeShip(Game.getID(), Authenticated.getID(), boat.getSize(), boat.getRowStart(), boat.getColStart(), boat.getRowEnd(), boat.getColEnd()))
                   return false;
           }
           return true;
       }
       else
           return false;
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
        boatsPlacement.removeAll(boatsPlacement);
    }
    
    
    private class BoatPlacement{
        private final int rowStart; 
        private final int colStart;
        private final int rowEnd; 
        private final int colEnd;
        private final int size;
        
        public BoatPlacement(int row, int col, int ssize, boolean horizontalOrientation){
            size = ssize;
            rowStart = row;
            colStart = col;
            if (horizontalOrientation){
                rowEnd = row;
                colEnd = col + size -1;
            }
            else {
                rowEnd = row + size -1;
                colEnd = col;
            }
        }
        
        public int getRowStart(){
            return rowStart;
        }
        
        public int getColStart(){
            return colStart;
        }
        
        public int getRowEnd(){
            return rowEnd;
        }
        
        public int getColEnd(){
            return colEnd;
        }
        
        public int getSize(){
            return size;
        }
        
    }
    
}
