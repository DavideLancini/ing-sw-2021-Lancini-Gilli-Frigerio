package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;

public class ClientMessageTakeResources extends ClientMessage {
    private boolean isRow;
    public int position;

    public ClientMessageTakeResources(boolean isRow, int position) {
        this.position = position;
        this.isRow = isRow;
        this.type = MessageType.TakeResources;
    }

    public boolean resolve(Controller controller) {
        return controller.takeResources(this.isRow, this.position);
    }
}
