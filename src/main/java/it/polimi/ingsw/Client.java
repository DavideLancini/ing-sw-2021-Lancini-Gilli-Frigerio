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

    private static Logger clientLogger = Logger.getLogger("ClientApp");
    public static void main( String[] args ) {
        clientLogger.setLevel(Level.ALL);
        boolean isON = true;
        if(CLIActionManager.Online()){
            ClientNetInterface net = CLIActionManager.Connect();
            while(isON){
                switch (CLIActionManager.showMainMenu()){
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