package it.polimi.ingsw.network.components;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.components.MessageType;

public class MessageTakeResources extends Message {
    private boolean isRow;
    public int position;

    public MessageTakeResources(boolean isRow, int position) {
        this.position = position;
        this.isRow = isRow;
        this.type = MessageType.TakeResources;
    }

    public boolean resolve(Controller controller) {
        return controller.takeResources(this.isRow, this.position);
    }
}
