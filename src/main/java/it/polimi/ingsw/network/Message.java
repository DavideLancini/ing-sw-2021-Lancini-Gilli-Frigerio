package it.polimi.ingsw.network;

import it.polimi.ingsw.network.messages.MessageType;

/**
 * Class Message
 * @author Group 12
 */
public abstract class Message {
    protected MessageType type;

    public MessageType getType(){return this.type;}
}
