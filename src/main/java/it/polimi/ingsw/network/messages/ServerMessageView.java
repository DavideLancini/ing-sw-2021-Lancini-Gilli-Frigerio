package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.ServerMessage;

public class ServerMessageView extends ServerMessage {
    public String view;

    public ServerMessageView(String view) {
        this.type = MessageType.View;
        this.view = view;
    }

}
