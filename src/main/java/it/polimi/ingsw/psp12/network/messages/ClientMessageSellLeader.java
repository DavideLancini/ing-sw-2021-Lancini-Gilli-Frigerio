package it.polimi.ingsw.psp12.network.messages;

import it.polimi.ingsw.psp12.controller.Controller;
import it.polimi.ingsw.psp12.network.DisconnectedException;
/**
 * Class ClientMessageSellLeader
 * @author Group 12
 */
public class ClientMessageSellLeader extends ClientMessage {
    private final int position;

    /**
     * constructor
     * @param position selected leader to activate (0=left, 1=right)
     */
    public ClientMessageSellLeader(int position) {
        this.position = position;
        this.type = MessageType.SellLeader;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        controller.sellLeader(this.position);
        return false;
    }

}
