package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.view.gui.GUIElement;

/**
 * Class ServerMessageView
 * @author gruppo 12
 */

public class ServerMessageView extends ServerMessage {
    private final String view;
    private String object;
    private GUIElement elem;

    /**
     * constructor CLI
     * @param view string to show
     */
    public ServerMessageView(String view) {
        this.type = MessageType.View;
        this.view = view;
    }

    /**
     * constructor GUI
     * @param view string to show
     * @param object object to show
     * @param elem GUIElement
     */
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
