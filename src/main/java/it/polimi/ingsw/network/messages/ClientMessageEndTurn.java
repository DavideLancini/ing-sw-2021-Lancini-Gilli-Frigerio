package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;

public class ClientMessageEndTurn extends ClientMessage {

    public ClientMessageEndTurn(){
        this.type = MessageType.EndTurn;
    }

    @Override
    public boolean resolve(Controller controller) throws Exception {
        throw new Exception(new EndTurnException());
    }
}
