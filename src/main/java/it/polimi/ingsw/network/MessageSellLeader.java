package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;

public class MessageSellLeader extends Message {
    private int position;

    public MessageSellLeader(int position) {
        this.position = position;
        this.type = MessageType.SellLeader;
    }

    public void resolve(Controller controller) {
        controller.sellLeader(this.position);
    }

}
