package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.view.CLIActionManager;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Client App
 *
 * @author Lancini Davide
 */
public class Client2 {
    public static Logger logger = Logger.getLogger("ClientApp");
    public static void main( String[] args ) {
        //Logger Creation
        logger.setLevel(Level.ALL);
        CLIActionManager.setLogger(logger);

        boolean isON = CLIActionManager.Online();
        if(isON){
            try {
                CLIActionManager.autoConnect();
            } catch (DisconnectedException e) {
                isON=false;
            }
            //ClientNetInterface net = CLIActionManager.Connect();

            String[] selection = CLIActionManager.showMainMenu();

            switch (selection[0]){
                //Join Match
                case "1":
                    try {
                        CLIActionManager.joinMatch(selection[1]);
                    } catch (DisconnectedException e) {
                        isON = false;
                    }
                    break;
                //Create Custom Match
                case "2":
                    try {
                        CLIActionManager.createCustomMatch(selection[1]);
                    } catch (DisconnectedException e) {
                        isON = false;
                    }
                    break;
                //Join Custom Match
                case "3":
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
        }else{
            //TODO: offline menu
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