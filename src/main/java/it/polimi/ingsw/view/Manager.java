package it.polimi.ingsw.view;

import it.polimi.ingsw.Client;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ServerMessageView;


import java.util.Collection;

public abstract class Manager {

    public abstract ClientMessage turn(boolean action);

    public abstract void view(ServerMessageView view);

    public abstract void displayError(String error);

    public abstract ClientMessage arrangeDepot(Collection<Resource> resources);

    public abstract ClientMessage chooseLeaders(String[] leaders);

    public abstract ClientMessage addResource();

    public abstract ClientMessage chooseResource(Resource[] resources);

    public abstract int joinMatch();

    public abstract void createCustomMatch(String s) throws DisconnectedException;

    public abstract String[] showMainMenu();

    public abstract boolean online();

    public ClientNetInterface autoConnect() throws DisconnectedException{
        return new ClientNetInterface("localhost",5555, Client.logger);
    }

    public abstract void waitForTurn();
}
