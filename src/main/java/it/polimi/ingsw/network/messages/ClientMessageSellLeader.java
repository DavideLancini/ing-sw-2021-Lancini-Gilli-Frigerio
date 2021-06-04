package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.DisconnectedException;

public class ClientMessageSellLeader extends ClientMessage {
    private int position;

    public ClientMessageSellLeader(int position) {
        this.position = position;
        this.type = MessageType.SellLeader;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        controller.sellLeader(this.position);
        return false;
    }

}
