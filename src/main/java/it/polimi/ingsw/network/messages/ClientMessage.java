package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.Message;

/**
 * Class ClientMessage
 * @author Group 12
 */
public abstract class ClientMessage extends Message {
    /**
     * makes action based on specific ClientMessage subclass
     * @param controller Controller of player
     * @return true if an action has been correctly performed
     */
    public boolean resolve(Controller controller) throws EndTurnException, DisconnectedException {return false;}
}