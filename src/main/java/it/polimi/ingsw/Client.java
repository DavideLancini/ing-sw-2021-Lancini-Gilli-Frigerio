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
public class Client {
    public static Logger logger = Logger.getLogger("ClientApp");
    public static void main( String[] args ) {
        //Logger Creation
        logger.setLevel(Level.ALL);
        //Ask Online-Offline
        boolean isON = CLIActionManager.Online();
        if(isON){
            try {

                CLIActionManager.autoConnect(); //Only for Testing
                //ClientNetInterface net = CLIActionManager.Connect();

                String[] selection = CLIActionManager.showMainMenu();
                switch (selection[0]){
                    case "1":
                        CLIActionManager.joinMatch(selection[1]);
                        break;
                    case "2":
                        CLIActionManager.createCustomMatch(selection[1]);
                        break;
                    case "3":
                        //TODO: Join Custom Match
                        break;
                    case "4":
                        //TODO: Create custom rule set
                        break;
                    case "5":
                        //TODO: Settings
                        break;
                    case "6":
                        //TODO: Credits
                        break;
                    case "7":
                        isON = false;
                        break;
                    default:
                        //Don't do anything and show again the main menu
                        break;
                }
            } catch (DisconnectedException e) {
                isON=false;
            }
        }else{
            //TODO: Offline menu
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