package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;

public class MessageTakeResources extends Message {
    private boolean isRow;
    protected int position;

    public MessageTakeResources(boolean isRow, int position) {
        this.position = position;
        this.isRow = isRow;
        this.type = MessageType.TakeResources;
    }

    public void resolve(Controller controller) {
        controller.takeResources(this.isRow, this.position);
    }
}
