package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.LeaderCard;

public class ServerMessageChooseLeaders extends ServerMessage{
    private LeaderCard[] leaders = new LeaderCard[4];

    public ServerMessageChooseLeaders(LeaderCard[] leaders){
        this.type = MessageType.ChooseLeaders;
        System.arraycopy(leaders, 0, this.leaders, 0, leaders.length);
    }

    public LeaderCard[] getLeaders(){return this.leaders;}
}
