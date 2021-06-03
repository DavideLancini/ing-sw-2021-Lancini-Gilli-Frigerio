package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Resource;

public class ClientMessageTryDepotConfiguration extends ClientMessage {
    private Resource[] input = new Resource[6];
    private int discardAmount;

    public ClientMessageTryDepotConfiguration(Resource[] input, int discard) {
        System.arraycopy(input, 0, this.input, 0, input.length);
        this.type = MessageType.TryDepotConfiguration;
        this.discardAmount = discard;
    }

    public boolean resolve(Controller controller) {
        controller.tryDepotConfiguration(this.input, discardAmount);
        return false;
    }
}
