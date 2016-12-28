package communicationserver;

import dataacess.DbUtils;
import java.util.Properties;
import java.io.*;

/**
 * Deals with generating all the sockets to communicate with 
 * multiple clients simultaneously.
 * 
 * @author Alunos-i221-16
 */
public class Server {
    private static int port = 4020;
    private static final boolean LISTENING = true;
    private static Properties props;
    
    private static final String START = "start server";
    private static final String CHECK = "check server";
    private static final String EXIT = "exit";
    
    /**
     * Class Constructor
     */
    public Server(){}
    
    /**
     * Main thread. This method verify and prepare hte connection do data base.
     * Create a new thread to do the connections with clients.
     * 
     * @param  args         command line arguments.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        props = new Properties();
        MultiServer multiServer;
        
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;
        
        if (args.length > 0) {
            if (args.length > 1) {
                System.out.println("It is only permitted one (or none) argument with port value");
                System.exit(-1);
            }
            
            port = Integer.parseInt(args[0]);
        }
        
        props.setProperty("dburl", "jdbc:postgresql://dbm.fe.up.pt/lpro1611");
        props.setProperty("dbpass", "G!7367hxf");
        props.setProperty("dbuser", "lpro1611");
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch ( ClassNotFoundException e) {
            System.out.println("Can't found postgres Driver");
            System.exit(-1);
        }
        
        multiServer = new MultiServer(port);
        
        DbUtils.INSTANCE.setConnectionParameters(props);
        
        System.out.println("Server console");
        System.out.print("< ");
        while (LISTENING) {
            if ((inputLine = userIn.readLine()) != null) {
                switch (inputLine) {
                    case START:
                        System.out.println("> Starting server...");
                        multiServer.start();
                        System.out.println("> The server is running");
                        break;
                        
                    case EXIT:
                        System.out.println("> The server will be closed");
                        System.exit(-1);
                        break;
                        
                    case CHECK:
                        if (multiServer.isAlive()) {
                            System.out.println("> The server is running");
                        } else {
                            System.out.println("> The server is stoped");
                        }
                        break;
                        
                    default:
                        System.out.println("> You need to use a valid command:");
                        System.out.println("\t·start server");
                        System.out.println("\t·check server");
                        System.out.println("\t·exit");
                        break;
                }
                System.out.print("< ");
            }
            
        }
    }
}
