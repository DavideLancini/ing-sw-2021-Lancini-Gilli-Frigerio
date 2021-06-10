package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.Resource;

public class ServerMessageTwoMarbleLeaders extends ServerMessage{
    Resource one, two;
    public ServerMessageTwoMarbleLeaders(Resource a, Resource b){
        this.type = MessageType.TwoMarbleLeaders;
        this.one = a;
        this.two = b;
    }

    public Resource[] getResources(){return new Resource[]{this.one, this.two};}
}
