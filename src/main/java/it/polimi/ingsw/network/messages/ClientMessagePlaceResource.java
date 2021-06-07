package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.DisconnectedException;

public class ClientMessagePlaceResource extends ClientMessage{
    private Resource resource;

    public ClientMessagePlaceResource(Resource resource){
        this.type=MessageType.PlaceResource;
        this.resource=resource;
    }

    public Resource getResource(){
        return this.resource;
    }
}
