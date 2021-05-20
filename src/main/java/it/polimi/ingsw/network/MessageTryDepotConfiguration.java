package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Resource;

public class MessageTryDepotConfiguration extends Message {
    private Resource[] input = new Resource[10];

    public MessageTryDepotConfiguration(Resource[] input) {
        System.arraycopy(input, 0, this.input, 0, input.length);
        this.type = MessageType.TryDepotConfiguration;
    }

    public void resolve(Controller controller) {
        controller.tryDepotConfiguration(this.input);
    }
}
