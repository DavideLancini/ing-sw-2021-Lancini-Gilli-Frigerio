package it.polimi.ingsw.psp12.network.messages;

import it.polimi.ingsw.psp12.controller.Controller;
/**
 * Class ClientMessageEndTurn
 * @author Group 12
 */
public class ClientMessageEndTurn extends ClientMessage {
    /**
     * constructor
     */
    public ClientMessageEndTurn(){
        this.type = MessageType.EndTurn;
    }

    @Override
    public boolean resolve(Controller controller) throws EndTurnException {
        throw new EndTurnException();
    }
}
