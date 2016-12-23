package businesslogicclient;

/**
 *
 * @author diogo
 */
public class Shot {
    private static int row;
    private static int col;
    private static boolean marked = false;
    private static boolean hit = false;
    
    public static void mark(int row, int col){
        Shot.row = row;
        Shot.col = col;
        Shot.marked = true;
    }
    
    public static void fire(){
        Shot.hit = true;
        Shot.marked = false;
    }
    public static int getRow(){
        return row;
    }
    public static int getCol(){
        return col;
    }
    public static boolean isMarked(){
        return Shot.marked;
    }
    public static boolean isHit(){
        return Shot.hit;
    }
}
