package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.LeaderCard;

public class ServerMessageChooseLeaders extends ServerMessage{
    private String[] leaders = new String[4];

    public ServerMessageChooseLeaders(String[] leaders){
        this.type = MessageType.ChooseLeaders;
        System.arraycopy(leaders, 0, this.leaders, 0, leaders.length);
    }

    public String[] getLeaders(){return this.leaders;}
}
