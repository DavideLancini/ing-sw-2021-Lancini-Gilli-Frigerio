package it.polimi.ingsw;

import it.polimi.ingsw.view.server.ServerView;
/**
 * Server App
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
        ServerView.loadParameters();

        while(isON){
            switch (ServerView.serverMenu()) {
                case "1":
                    ServerView.toggleServer();
                    break;
                case "2":
                    ServerView.editParameters();
                    break;
                case "3":
                    isON = false;
                    break;
                default:
                    break;
            }
        }
    }
}