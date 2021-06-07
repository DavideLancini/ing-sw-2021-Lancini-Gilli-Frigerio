package it.polimi.ingsw.view;

import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.DisconnectedException;

import java.util.Collection;

public abstract class Manager {

    public ClientMessage turn(boolean action){
        return null;
    }

    public void view(String view){}

    public void displayError(String error){}

    public ClientMessage arrangeDepot(Collection<Resource> resources){
        return null;
    }

    public ClientMessage chooseLeaders(String[] leaders) {return null;}

    public ClientMessage addResource() {return null;}
}
