package it.polimi.ingsw;

import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.view.CLIActionManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client App
 *
 * @author Lancini Davide
 */
public class Client {

    public static Logger logger = Logger.getLogger("ClientApp");
    public static void main( String[] args ) {
        logger.setLevel(Level.FINE);
        boolean isON = true;
        if(CLIActionManager.Online()){
            ClientNetInterface net = CLIActionManager.Connect();
            while(isON){
                switch (CLIActionManager.showMainMenu()){
                    case "1":
                        CLIActionManager.createMatch(net);
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
