package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
/**
 * Class ClientMessageEndTurn
 * @author gruppo 12
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
