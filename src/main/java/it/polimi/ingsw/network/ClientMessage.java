package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.messages.MessageType;

public abstract class ClientMessage extends Message{


    /**
     *
     * @param controller
     * @return true if an action has been correctly performed
     */
    public boolean resolve(Controller controller) throws Exception {return false;}
}

