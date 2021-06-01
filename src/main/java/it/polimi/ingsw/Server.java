package it.polimi.ingsw;

import it.polimi.ingsw.view.server.ServerMainMenu;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server App
 *
 * @author Lancini Davide
 */
public class Server {
    public static Logger logger = Logger.getLogger("ServerApp");
    public static void main( String[] args ){
        logger.setLevel(Level.ALL);
        boolean isON = true;

        ServerMainMenu.loadParameters();
        while(isON){
            switch (ServerMainMenu.serverMenu()) {
                case "1":
                    ServerMainMenu.toggleServer();
                    break;
                case "2":
                    ServerMainMenu.editParameters();
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

//Logger Levels
//Level.OFF     -> Nothing is logged
//Level.SEVERE  -> Fatal Error
//Level.WARNING -> Big Error
//Level.INFO    -> Important Messages that should always be in the console
//Level.CONFIG  -> Important Messages for debug
//Level.FINE    -> Recoverable Failure
//Level.FINER   -> Logging calls for entering, returning, or throwing an exception
//Level.FINEST  -> Highly detailed tracking
//Level.ALL     -> Everything is logged