package it.polimi.ingsw;

import it.polimi.ingsw.view.ServerView;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server App
 *
 * @author Lancini Davide
 */
public class Server {
    public static void main( String[] args ){
        //Logger Creation
        Logger logger = Logger.getLogger("ServerApp");
        logger.setLevel(Level.ALL);
        ServerView.setLogger(logger);
        //Loading parameters from the properties file or standard par. if FNF
        ServerView.loadParameters();
        //Main Cycle of the Server Interface
        boolean isON = true;

        //TURN SERVER ON RIGHT AWAY
        ServerView.toggleServer();

        while(isON){
            //Asking for a command
            switch (ServerView.serverMenu()) {
                case "1":
                    //Switch server ON/OFF
                    ServerView.toggleServer();
                    break;
                case "2":
                    //Edit server parameters, some parameters require a reboot (localport)
                    ServerView.editParameters();
                    break;
                case "3":
                    //Shutdown WITHOUT save, turn OFF the server first to save all the matches
                    isON = false;
                    System.exit(0);
                    break;
                default:
                    //Invalid Command: just re-prompt the Menu
                    break;
            }
        }
    }
}

//Logger Levels, suggested OFF for normal use
//Level.OFF     -> Nothing is logged
//Level.SEVERE  -> Fatal Error
//Level.WARNING -> Big Error
//Level.INFO    -> Important Messages that should always be in the console
//Level.CONFIG  -> Important Messages for debug
//Level.FINE    -> Recoverable Failure
//Level.FINER   -> Logging calls for entering, returning, or throwing an exception
//Level.FINEST  -> Highly detailed tracking
//Level.ALL     -> Everything is logged