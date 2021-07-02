package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.ClientMessageJoinGame;
import it.polimi.ingsw.view.CLIActionManager;
import it.polimi.ingsw.view.Log;
import it.polimi.ingsw.view.Manager;
import it.polimi.ingsw.view.gui.menus.GUIActionManager;

import java.util.logging.Level;

/**
 * Client App
 */
public class Client {
    public static boolean isOn = true;

    public static void run(String version) {

        //Logger Setup
        Log.logger.setLevel(Level.OFF);

        Manager manager;
        if(version.equals("cli"))manager = new CLIActionManager();
        else manager = new GUIActionManager();

        //Ask Online-Offline
        boolean isOnline = manager.online();

        while(isOn){
            if (isOnline) {
                try {
                    //ClientNetInterface net = manager.autoConnect(); //Only for Testing
                    ClientNetInterface net = manager.connect();

                    String[] selection = manager.showOnlineMenu();
                    switch (selection[0]) {
                        case "1":

                            int players = manager.joinMatch();
                            if(players < 1) break; //Used in GUI if dialog is closed, shouldn't happen in cli
                            ClientMessageJoinGame messageJoin = new ClientMessageJoinGame(selection[1], players);
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
                    //Display error, then quit
                    manager.displayError("You are no longer connected to the server.");
                    isOn = false;
                }
            } else {
                switch (manager.showOfflineMenu()) {
                    case "1":
                        manager.startOfflineGame(manager);
                        //offline server doesn't handle recreation
                        //close client at the end
                        isOn = false;
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
            manager.close();
            if(version.equals("cli"))manager = new CLIActionManager();
            else manager = new GUIActionManager();
        }
        System.exit(0);
    }
}