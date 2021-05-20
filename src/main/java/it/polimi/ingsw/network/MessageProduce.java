package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;

public class MessageProduce extends Message {
    private boolean[] activated = new boolean[6];

    public MessageProduce(boolean[] activated) {
        System.arraycopy(activated, 0, this.activated, 0, activated.length);
        this.type = MessageType.Produce;
    }

    public void resolve(Controller controller) {
        controller.produce(this.activated);
    }

}
