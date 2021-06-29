package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.NetInterface;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageJoinGame;
import it.polimi.ingsw.network.messages.ServerMessageChooseLeaders;
import it.polimi.ingsw.network.messages.ServerMessageView;

import java.util.Collection;

/**
 * Class Manager
 * @author Group 12
 */
public abstract class Manager {

    public abstract ClientMessage turn(boolean action);

    public abstract void view(ServerMessageView view);

    public abstract void displayError(String error);

    public abstract ClientMessage arrangeDepot(Collection<Resource> resources);

    public abstract ClientMessage chooseLeaders(ServerMessageChooseLeaders leaders);

    public abstract ClientMessage addResource();

    public abstract ClientMessage chooseResource(Resource[] resources);

    public abstract int joinMatch();

    public abstract String[] showOnlineMenu();

    public abstract boolean online();

    public ClientNetInterface autoConnect() throws DisconnectedException{
        return new ClientNetInterface("localhost",5555);
    }

    public abstract void waitForTurn();



    public static void assignDepot(Collection<Resource> resources, Resource[] newResources, Resource[] choice) {
        if(resources.remove(choice[0])) newResources[0] = choice[0];
        if(resources.remove(choice[1])) newResources[1] = choice[1];
        if(resources.remove(choice[1])) newResources[2] = choice[1];
        if(resources.remove(choice[2])) newResources[3] = choice[2];
        if(resources.remove(choice[2])) newResources[4] = choice[2];
        if(resources.remove(choice[2])) newResources[5] = choice[2];
    }

    public abstract String showOfflineMenu();

    public void startOfflineGame(Manager manager){
        //Create Local Interface
        NetInterface net = new NetInterface();
        Log.logger.info("Net Created");

        try {
            //Create Local Player
            Player offlinePlayer = new Player();
            Log.logger.info("OK Player");
            //Start the Player on a thread
            offlinePlayer.start();
            Log.logger.info("OK Start");

            //Ask for a Local Game
            net.send(new ClientMessageJoinGame("Offline", 1));
            Log.logger.info("OK Message");

            ClientController controller = new ClientController(manager);
            Log.logger.info("OK Controller");
            controller.setup(net);
            Log.logger.info("OK Setup");


            controller.main();
            Log.logger.info("OK Main");
        } catch (Exception e) {
            Log.logger.warning("ERROR");
        }
    }

    public abstract void showCredits();
}
