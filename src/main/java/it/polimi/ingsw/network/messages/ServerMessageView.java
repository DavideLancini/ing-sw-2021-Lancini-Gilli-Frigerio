package it.polimi.ingsw.network.messages;

import javax.swing.*;

public class ServerMessageView extends ServerMessage {
    private final String view;
    private JPanel panel;

    public ServerMessageView(String view) {
        this.type = MessageType.View;
        this.view = view;
    }

    public ServerMessageView(String view, JPanel panel) {
        this.type = MessageType.View;
        this.view = view;
        this.panel = panel;
    }

    public Object getView(boolean string) {
        return string ? this.view : this.panel;
    }


}
