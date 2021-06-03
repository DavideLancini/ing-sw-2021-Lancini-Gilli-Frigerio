package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Resource;

public class ClientMessageTryDepotConfiguration extends ClientMessage {
    private Resource[] input = new Resource[10];

    public ClientMessageTryDepotConfiguration(Resource[] input) {
        System.arraycopy(input, 0, this.input, 0, input.length);
        this.type = MessageType.TryDepotConfiguration;
    }

    public boolean resolve(Controller controller) {
        controller.tryDepotConfiguration(this.input);
        return false;
    }
}
