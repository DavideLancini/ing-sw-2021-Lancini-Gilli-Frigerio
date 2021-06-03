package it.polimi.ingsw.view;

import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.DisconnectedException;

import java.util.Collection;

public abstract class Manager {

    public ClientMessage Turn(boolean action){
        return null;
    }

    public void View(String view){}

    public void DisplayError(String error){}

    public ClientMessage ArrangeDepot(Collection<Resource> resources) throws DisconnectedException {
        return null;
    }

    public ClientMessage ChooseLeaders(LeaderCard[] leaders) {return null;};
}
