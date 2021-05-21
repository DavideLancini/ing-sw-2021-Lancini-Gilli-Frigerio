package it.polimi.ingsw.network.components;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.components.MessageType;

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
