package it.polimi.ingsw.network.components;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.components.MessageType;

public class MessageSetResource extends Message {
    private Resource resource;
    private int position;

    //0 to 1 for defaultProduciton input, 2 for output, 3 to 4 for LeaderProduction choice

    public MessageSetResource(Resource resource, int position) {
        this.resource = resource;
        this.position = position;
        this.type = MessageType.SetResource;
    }

    public void resolve(Controller controller) {
        controller.setResource(position, resource);
    }
}
