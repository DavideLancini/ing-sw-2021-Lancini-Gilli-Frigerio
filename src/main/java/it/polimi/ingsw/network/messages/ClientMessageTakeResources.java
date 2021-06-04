package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.DisconnectedException;

public class ClientMessageTakeResources extends ClientMessage {
    private boolean isRow;
    public int position;

    public ClientMessageTakeResources(boolean isRow, int position) {
        this.position = position;
        this.isRow = isRow;
        this.type = MessageType.TakeResources;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        return controller.takeResources(this.isRow, this.position);
    }
}
