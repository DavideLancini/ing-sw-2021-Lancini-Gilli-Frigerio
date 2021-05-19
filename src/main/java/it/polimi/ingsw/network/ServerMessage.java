package it.polimi.ingsw.network;

import it.polimi.ingsw.model.Resource;

import java.util.Arrays;
import java.util.Collection;

public abstract class ServerMessage {
    protected ServerMessageType type;
}

class ServerMessaggeTurn extends ServerMessage{

    public ServerMessaggeTurn(){
        this.type = ServerMessageType.Turn;
    }

}

