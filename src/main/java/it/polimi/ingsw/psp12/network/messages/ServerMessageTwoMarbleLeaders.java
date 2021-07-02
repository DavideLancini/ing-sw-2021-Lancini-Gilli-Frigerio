package it.polimi.ingsw.psp12.network.messages;

import it.polimi.ingsw.psp12.model.Resource;
/**
 * Class ServerMessageTwoMarbleLeaders
 * @author Group 12
 */
public class ServerMessageTwoMarbleLeaders extends ServerMessage{
    final Resource one;
    final Resource two;

    /**
     * constructor
     * @param a first leaderTransform resource type
     * @param b second leaderTransform resource type
     */
    public ServerMessageTwoMarbleLeaders(Resource a, Resource b){
        this.type = MessageType.TwoMarbleLeaders;
        this.one = a;
        this.two = b;
    }

    public Resource[] getResources(){return new Resource[]{this.one, this.two};}
}
