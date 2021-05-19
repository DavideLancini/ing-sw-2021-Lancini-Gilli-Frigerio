package it.polimi.ingsw.network;

import it.polimi.ingsw.model.Resource;

import java.util.Collection;

public class ServerMessageMarketReturn extends ServerMessage{
    private Collection<Resource> resources;

    public ServerMessageMarketReturn(Collection<Resource> resources){
        this.type = ServerMessageType.MarketReturn;
        this.resources = resources;
    }

}
