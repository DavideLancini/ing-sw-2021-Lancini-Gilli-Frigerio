package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.Resource;

import java.util.Collection;

public class ServerMessageMarketReturn extends ServerMessage {
    private Collection<Resource> resources;

    public ServerMessageMarketReturn(Collection<Resource> resources){
        this.type = MessageType.MarketReturn;
        this.resources = resources;
    }

    public Collection<Resource> getResources(){return this.resources;}

}
