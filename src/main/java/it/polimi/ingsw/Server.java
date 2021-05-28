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
        logger.setLevel(Level.FINE);
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


//Server.logger.log(Level.SEVERE,"class>method> error"); Fatal Error
//Server.logger.log(Level.WARNING,"class>method> error, solution"); Solvable Error
//Server.logger.log(Level.INFO,"class>method> communication"); Info

//Server.logger.log(Level.CONFIG,"class>method> error, solution");
//Server.logger.log(Level.FINE,"class>method> error, solution");
//Server.logger.log(Level.FINER,"class>method> error, solution");
//Server.logger.log(Level.FINEST,"class>method> error, solution");