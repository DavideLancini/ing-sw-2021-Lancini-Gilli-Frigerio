package it.polimi.ingsw.psp12.view;

import it.polimi.ingsw.psp12.controller.ClientController;
import it.polimi.ingsw.psp12.controller.Player;
import it.polimi.ingsw.psp12.model.Resource;
import it.polimi.ingsw.psp12.network.ClientNetInterface;
import it.polimi.ingsw.psp12.network.DisconnectedException;
import it.polimi.ingsw.psp12.network.NetInterface;
import it.polimi.ingsw.psp12.network.messages.ClientMessage;
import it.polimi.ingsw.psp12.network.messages.ClientMessageJoinGame;
import it.polimi.ingsw.psp12.network.messages.ServerMessageChooseLeaders;
import it.polimi.ingsw.psp12.network.messages.ServerMessageView;

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

// --Commented out by Inspection START (01/07/2021 17:09):
//    public ClientNetInterface autoConnect() throws DisconnectedException{
//        //ask address
//        System.out.println("server address:");
//        String address= Reader.in.nextLine();
//        return new ClientNetInterface(address,5555, 0);
//    }
// --Commented out by Inspection STOP (01/07/2021 17:09)

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
            e.printStackTrace();
        }
    }

    public abstract void showCredits();

    public abstract ClientNetInterface connect() throws DisconnectedException;

    public abstract void close();
}
