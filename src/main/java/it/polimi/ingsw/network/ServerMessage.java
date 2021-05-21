package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.ServerMessageType;

public abstract class ServerMessage {
    protected ServerMessageType type;
}

class ServerMessaggeTurn extends ServerMessage{

    public ServerMessaggeTurn(){
        this.type = ServerMessageType.Turn;
    }

}

class ServerMessaggeView extends ServerMessage{
    String view;

    public ServerMessaggeView(String view){
        this.type = ServerMessageType.View;
        this.view = view;
    }

}
