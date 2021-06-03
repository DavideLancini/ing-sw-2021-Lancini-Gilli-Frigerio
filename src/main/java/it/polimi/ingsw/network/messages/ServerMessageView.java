package it.polimi.ingsw.network.messages;

public class ServerMessageView extends ServerMessage {
    public String view;

    public ServerMessageView(String view) {
        this.type = MessageType.View;
        this.view = view;
    }

}
