package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.ClientMessageJoinGame;
import it.polimi.ingsw.view.CLIActionManager;
import it.polimi.ingsw.view.Manager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client App
 */
public class Client {
    public static Logger logger = Logger.getLogger("ClientApp");
    public static boolean isOn = true;

    public static void main(String[] args) {

        Manager manager = new CLIActionManager();
        //Logger Creation
        logger.setLevel(Level.ALL);
        //Ask Online-Offline
        boolean isOnline = manager.online();
        while(isOn){
            if (isOnline) {
                try {
                    ClientNetInterface net = manager.autoConnect(); //Only for Testing
                    //CLIActionManager.Connect();

                    String[] selection = manager.showOnlineMenu();
                    switch (selection[0]) {
                        case "1":

                            ClientMessageJoinGame messageJoin = new ClientMessageJoinGame(selection[1], manager.joinMatch());
                            net.send(messageJoin);
                            ClientController controller = new ClientController(manager);
                            controller.setup(net);
                            controller.main();

                            break;
                        case "2":
                            manager.showCredits();
                            break;
                        case "3":
                            isOn = false;
                            break;
                        default:
                            //Don't do anything and show again the main menu
                            break;
                    }
                } catch (DisconnectedException e) {
                    isOn = false;
                }
            } else {
                switch (manager.showOfflineMenu()) {
                    case "1":
                        manager.startOfflineGame(manager);
                        break;
                    case "2":
                        manager.showCredits();
                        break;
                    case "3":
                        isOn = false;
                        break;
                    default:
                        //Don't do anything and show again the main menu
                        break;
                }
            }
        }

    }
}