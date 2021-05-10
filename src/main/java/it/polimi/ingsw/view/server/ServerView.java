package it.polimi.ingsw.view.server;

import it.polimi.ingsw.Server;

/**
 * Interface for the Server
 * @author Lancini Davide
 */
public class ServerView {
    /**
     * Simple feature to keep the console clean, tested on windows, mac os and son linux distros
     * if the logLevel is set to Verbose the Console will not be cleaned
     * @author Lancini Davide
     */
    public static void clearConsole(){
        if(Server.logLevel != 2) {
            try {
                final String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    Runtime.getRuntime().exec("cls");
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            } catch (final Exception e) {
                // TODO Handle exceptions.
            }
        }
    }
    /**
     * Load preferred port number and max slot for match
     * @author Lancini Davide
     */
    public static void loadParameters(){
        // TODO load parameters from file

    }
    /**
     * Print server status and the available commands
     * @author Lancini Davide
     */
    public static void serverStatus(){
        if(checkServerActivity()){
            System.out.println( "The server is ON" );
            // TODO print server parameters
            System.out.println( "1. Stop Server" );
        }
        else{
            System.out.println( "The server is OFF" );
            System.out.println( "1. Start Server" );
        }
        System.out.println( "2. Edit server parameters" );
        System.out.println( "3. Exit" );
    }
    /**
     * Check if the sockets are online
     * @return true if both sockets are online
     * if only one is online the server will be considered offline
     * @author Lancini Davide
     */
    public static boolean checkServerActivity() {
        // TODO check if the socket are (both) online
        return true; // temporary
    }
    /**
     * Take input from the user and do what is asked
     * @author Lancini Davide
     */
    public static void takeAction(){
        String action;
        action = System.console().readLine();
        switch(action){
            case "1":
                // toggle server on/off
                break;
            case "2":
                // edit parameters
                break;
            case "3":
                // close without save
                break;
            default:
                // error in input, just refresh
                break;
        }
    }
}
