package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.ServerMessageType;

public abstract class ServerMessage extends Message {
    protected ServerMessageType type;

    public ServerMessageType getType(){
        return this.type;
    }
}

