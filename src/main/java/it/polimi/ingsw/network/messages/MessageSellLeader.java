package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.Message;

public class MessageSellLeader extends Message {
    private int position;

    public MessageSellLeader(int position) {
        this.position = position;
        this.type = MessageType.SellLeader;
    }

    public boolean resolve(Controller controller) {
        controller.sellLeader(this.position);
        return false;
    }

}
