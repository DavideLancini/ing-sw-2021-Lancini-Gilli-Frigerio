package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.ServerMessage;
import it.polimi.ingsw.network.components.ServerMessageType;

public class ServerMessageView extends ServerMessage {
    public String view;

    public ServerMessageView(String view) {
        this.type = ServerMessageType.View;
        this.view = view;
    }

}
