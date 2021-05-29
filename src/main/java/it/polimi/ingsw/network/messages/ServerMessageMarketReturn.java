package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ServerMessage;

import java.util.Collection;

public class ServerMessageMarketReturn extends ServerMessage {
    private Collection<Resource> resources;

    public ServerMessageMarketReturn(Collection<Resource> resources){
        this.type = ServerMessageType.MarketReturn;
        this.resources = resources;
    }

    public Collection<Resource> getResources(){return this.resources;}

}
