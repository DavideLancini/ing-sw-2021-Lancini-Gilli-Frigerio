package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.DisconnectedException;

public class ClientMessageProduce extends ClientMessage {
    private boolean[] activated = new boolean[6];

    public ClientMessageProduce(boolean[] activated) {
        System.arraycopy(activated, 0, this.activated, 0, activated.length);
        this.type = MessageType.Produce;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        return controller.produce(this.activated);
    }

}
