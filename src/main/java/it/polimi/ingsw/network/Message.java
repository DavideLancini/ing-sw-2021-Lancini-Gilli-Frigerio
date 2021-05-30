package it.polimi.ingsw.network;

import it.polimi.ingsw.network.messages.MessageType;

public abstract class Message {
    protected MessageType type;

    public MessageType getType(){return this.type;}
}
