package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.DisconnectedException;
/**
 * Class ClientMessageProduce
 * @author Group 12
 */
public class ClientMessageProduce extends ClientMessage {
    private final boolean[] activated = new boolean[6];

    /**
     * constructor
     * @param activated true if production is selected (for all those available)
     */
    public ClientMessageProduce(boolean[] activated) {
        System.arraycopy(activated, 0, this.activated, 0, activated.length);
        this.type = MessageType.Produce;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        return controller.produce(this.activated);
    }

}
