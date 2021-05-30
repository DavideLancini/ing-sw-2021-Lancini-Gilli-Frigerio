package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.ClientMessage;

public class ClientMessageEndTurn extends ClientMessage {

    public ClientMessageEndTurn(){
        this.type = MessageType.EndTurn;
    }
}
