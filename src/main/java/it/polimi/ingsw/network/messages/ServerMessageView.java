package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.view.gui.GUIElement;

public class ServerMessageView extends ServerMessage {
    private final String view;
    private String object;
    private GUIElement elem;

    public ServerMessageView(String view) {
        this.type = MessageType.View;
        this.view = view;
    }

    public ServerMessageView(String view, String object, GUIElement elem) {
        this.type = MessageType.View;
        this.view = view;
        this.object = object;
        this.elem = elem;

    }

    public String getView(boolean string) {
        return string ? this.view : this.object;
    }
    public GUIElement getElem(){return elem;}

}
