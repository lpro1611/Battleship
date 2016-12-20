/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogicserver;

/**
 *
 * @author CPU TOSH
 */
public class Ship {
    int size;
    int hits;
    
    public Ship( int size){
        this.size=size;
        this.hits=0;
        
    }    
    
    public int hit(){
        if(size==(++hits))
            return 1;
        else 
            return 0;
 
    }
}
