package businesslogicclient;

import communicationclient.Protocol;

/**
 *
 * @author diogo
 */
public class Challenge {
    private static int[] id;
    private static String[] username;
    private static boolean accepted = false;
    
    public static String[] getList(){
        String[] list;
        list = Protocol.getChallengeList(Authenticated.getID());
        if(list != null){
            Challenge.id = new int[list.length/2];
            Challenge.username = new String[list.length/2];
            for (int i=0; i<list.length; i+=2){
                id[i/2] = Integer.parseInt(list[i]);
                username[i/2] = list[i + 1];
            }
            return username;
        }
        else{
            return null;
        }
    }
    
    public static int getID(String name){
        for (int i = 0; i < username.length; i++){
            if (username[i].equals(name)){
                return id[i];
            }
        }
        return 0;
    }
    
    public static boolean wasAccepted(){
        return accepted;
    }
    
    public static void accept(boolean accepted){
        Challenge.accepted = accepted;
    }
}
