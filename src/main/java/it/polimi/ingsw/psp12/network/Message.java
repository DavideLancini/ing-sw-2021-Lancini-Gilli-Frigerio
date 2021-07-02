package it.polimi.ingsw.psp12.network;

import it.polimi.ingsw.psp12.network.messages.MessageType;

/**
 * Class Message
 * @author Group 12
 */
public abstract class Message {
    protected MessageType type;

    public MessageType getType(){return this.type;}
}
