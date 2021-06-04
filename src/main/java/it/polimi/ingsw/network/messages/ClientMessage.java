package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.Message;

public abstract class ClientMessage extends Message {
    /**
     *
     * @param controller
     * @return true if an action has been correctly performed
     */
    public boolean resolve(Controller controller) throws EndTurnException, DisconnectedException {return false;}
}