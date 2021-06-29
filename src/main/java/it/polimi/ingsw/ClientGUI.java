package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.ClientMessageJoinGame;
import it.polimi.ingsw.view.Log;
import it.polimi.ingsw.view.Manager;
import it.polimi.ingsw.view.gui.menus.GUIActionManager;

import java.util.logging.Level;

/**
 * Client App
 */
public class ClientGUI {

    public static void main(String[] args) {

        Manager manager = new GUIActionManager();

        Log.logger.setLevel(Level.ALL);

        //Ask Online-Offline
        boolean isON = manager.online();
        if (isON) {
            try {

                ClientNetInterface net = manager.autoConnect(); //Only for Testing
                //CLIActionManager.Connect();

                String[] selection = manager.showOnlineMenu();
                Log.logger.info(selection[0]);
                Log.logger.info(selection[1]);

                switch (selection[0]) {
                    case "1":
                        Log.logger.info("joining game");
                        int players = manager.joinMatch();
                        if(players < 1) break;
                        ClientMessageJoinGame messageJoin = new ClientMessageJoinGame(selection[1], players);
                        net.send(messageJoin);
                        ClientController controller = new ClientController(manager);
                        controller.setup(net);
                        controller.main();
                        break;

                    case "2":
                        // manager.createCustomMatch(selection[1]);
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
                isON = false;
            }
        } else {
            //TODO: Offline menu
        }
    }
}