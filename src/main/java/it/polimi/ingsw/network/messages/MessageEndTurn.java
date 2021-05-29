package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.Message;

public class MessageEndTurn extends Message {

    public MessageEndTurn(){
        this.type = MessageType.EndTurn;
    }
}
