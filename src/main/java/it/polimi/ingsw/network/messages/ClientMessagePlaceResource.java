package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.Resource;

public class ClientMessagePlaceResource extends ClientMessage{
    private final Resource resource;

    public ClientMessagePlaceResource(Resource resource){
        this.type=MessageType.PlaceResource;
        this.resource=resource;
    }

    public Resource getResource(){
        return this.resource;
    }
}
