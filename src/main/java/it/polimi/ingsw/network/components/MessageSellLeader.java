package it.polimi.ingsw.network.components;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.components.MessageType;

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
