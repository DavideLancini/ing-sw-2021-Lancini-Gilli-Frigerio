package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.Resource;

import java.util.Collection;

/**
 * Class ServerMessageMarketReturn
 * @author Group 12
 */
public class ServerMessageMarketReturn extends ServerMessage {
    private final Collection<Resource> resources;

    /**
     * constructor
     * @param resources resources taken from market
     */
    public ServerMessageMarketReturn(Collection<Resource> resources){
        this.type = MessageType.MarketReturn;
        this.resources = resources;
    }

    public Collection<Resource> getResources(){return this.resources;}

}
