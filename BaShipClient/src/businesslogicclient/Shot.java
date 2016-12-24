package businesslogicclient;

import communicationclient.Protocol;

/**
 *
 * @author diogo
 */
public class Shot {
    private static int row;
    private static int col;
    private static boolean marked = false;
    private static boolean hit = false;
    private static boolean criticalHit = false;
    private static String boatName;
    
    public static void mark(int row, int col){
        Shot.row = row;
        Shot.col = col;
        Shot.marked = true;
    }
    public static void setHit(boolean hit){
        Shot.hit = hit;
    }
    
    public static void setCriticalHit(boolean criticalHit){
        Shot.criticalHit = criticalHit;
    }
    
    public static void setBoadName(String boatName){
        Shot.boatName = boatName;
    }
    
    public static boolean fire(){
        if(Protocol.fireShot(Game.getID(), Authenticated.getID(), row, col)){
            Shot.marked = false;
            return true;
        }
        else{
            return false;
        }
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
    public static boolean isCriticalHit(){
        return Shot.criticalHit;
    }
    public static String getBoatName(){
        return Shot.boatName;
    }
}
