package it.polimi.ingsw.psp12.network.messages;

import it.polimi.ingsw.psp12.model.Resource;

/**
 * Class ClientMessagePlaceResource
 * @author Group 12
 */
public class ClientMessagePlaceResource extends ClientMessage{
    private final Resource resource;

    /**
     * constructor
     * @param resource resource to deposit
     */
    public ClientMessagePlaceResource(Resource resource){
        this.type=MessageType.PlaceResource;
        this.resource=resource;
    }

    public Resource getResource(){
        return this.resource;
    }
}
