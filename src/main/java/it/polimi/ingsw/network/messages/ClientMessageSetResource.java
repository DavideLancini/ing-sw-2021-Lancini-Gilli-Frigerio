package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Resource;
/**
 * Class ClientMessageSetResource
 * @author Group 12
 */
public class ClientMessageSetResource extends ClientMessage {
    private final Resource resource;
    private final int position;

    /**
     * constructor
     * @param resource resource to set
     * @param position 0 to 1 for defaultProduction input, 2 for output, 3 to 4 for LeaderProduction choice
     */
    public ClientMessageSetResource(Resource resource, int position) {
        this.resource = resource;
        this.position = position;
        this.type = MessageType.SetResource;
    }

    public boolean resolve(Controller controller) {
        controller.setResource(position, resource);
        return false;
    }
}