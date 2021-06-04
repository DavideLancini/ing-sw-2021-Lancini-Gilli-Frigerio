package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.DisconnectedException;

public class ClientMessageTryDepotConfiguration extends ClientMessage {
    private Resource[] input = new Resource[6];
    private int discardAmount;

    public ClientMessageTryDepotConfiguration(Resource[] input, int discard) {
        System.arraycopy(input, 0, this.input, 0, input.length);
        this.type = MessageType.TryDepotConfiguration;
        this.discardAmount = discard;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        controller.tryDepotConfiguration(this.input, discardAmount);
        return false;
    }
}
