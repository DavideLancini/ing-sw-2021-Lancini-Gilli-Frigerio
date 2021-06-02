package it.polimi.ingsw;

import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.view.CLIActionManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client App
 *
 * @author Lancini Davide
 */
public class Client {
    public static void main( String[] args ) {
        //Logger Creation
        Logger logger = Logger.getLogger("ClientApp");
        logger.setLevel(Level.ALL);
        CLIActionManager.setLogger(logger);

        boolean isON = true;
        if(CLIActionManager.Online()){
            //TODO: THIS IS ONLY FOR TESTING
            ClientNetInterface net = new ClientNetInterface();
            net.setParameters("localhost", 5555, 1001, logger);
            try {
                net.connect();
            } catch (DisconnectedException e) {
                //FAILED TO CONNECT
            }
            //TODO: THIS IS ONLY FOR TESTING

            //ClientNetInterface net = CLIActionManager.Connect();

            while(isON){
                String selection = CLIActionManager.showMainMenu();
                switch (selection){
                    case "1":
                        try {
                            CLIActionManager.createMatch(net);
                        } catch (DisconnectedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2":
                        //enter join match
                        break;
                    case "3":
                        //enter view open match
                        break;
                    case "4":
                        //enter create custom rule set
                        break;
                    case "5":
                        //enter settings
                        break;
                    case "6":
                        //enter credits
                        break;
                    case "7":
                        isON = false;
                        break;
                    default:
                        // don't do anything and show again the main menu
                        break;
                }
            }
        }else{
            //TODO: versione offline del menu
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