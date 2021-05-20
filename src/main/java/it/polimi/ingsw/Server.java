package it.polimi.ingsw;

import it.polimi.ingsw.view.server.ServerView;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server App
 *
 * @author Lancini Davide
 */
public class Server {
    public static Logger logger = Logger.getLogger("ServerApp");

    public static void main( String[] args )
    {
        logger.setLevel(Level.FINE);

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


//Server.logger.log(Level.SEVERE,"class>method> error"); Fatal Error
//Server.logger.log(Level.WARNING,"class>method> error, solution"); Solvable Error
//Server.logger.log(Level.INFO,"class>method> error, solution"); Info

//Server.logger.log(Level.CONFIG,"class>method> error, solution");
//Server.logger.log(Level.FINE,"class>method> error, solution");
//Server.logger.log(Level.FINER,"class>method> error, solution");
//Server.logger.log(Level.FINEST,"class>method> error, solution");