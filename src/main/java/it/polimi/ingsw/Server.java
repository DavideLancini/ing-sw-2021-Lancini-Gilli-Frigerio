package it.polimi.ingsw;

import static it.polimi.ingsw.view.server.ServerView.*;
/**
 * Main Server Class
 * logLevel 0 is OFF
 * logLevel 1 is ERROR ONLY
 * logLevel 2 is VERBOSE
 * @author Lancini Davide
 */
public class Server {
    public static void main( String[] args )
    {
        int logLevel = 0;
        boolean isON = true;
        while(isON){
            clearConsole();
            loadParameters();
            serverStatus(logLevel);
            takeAction();
            isON = checkServerActivity();
        }
    }
}