package it.polimi.ingsw;

import static it.polimi.ingsw.view.server.ServerView.*;
/**
 * Main Server Class
 * logLevel 0 is SILENT (FATAL ERROR ONLY)
 * logLevel 1 is ERROR AND WARNING
 * logLevel 2 is VERBOSE
 * @author Lancini Davide
 */
public class Server {
    public static int logLevel = 1;
    public static void main( String[] args )
    {
        boolean isON = true;
        while(isON){
            clearConsole();
            loadParameters();
            serverStatus();
            takeAction();
            isON = checkServerActivity();
        }
    }
}