package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ClientMessage;
import it.polimi.ingsw.network.DisconnectedException;

import java.util.Collection;

public abstract class Manager {

    public ClientMessage Turn(boolean action){
        return null;
    }

    public void View(String view){}

    public void DisplayError(String error){}

    public void ArrangeDepot(Collection<Resource> resources) throws DisconnectedException {}

}
