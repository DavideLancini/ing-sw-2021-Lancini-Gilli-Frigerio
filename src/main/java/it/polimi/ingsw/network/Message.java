package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.*;

public abstract class Message {

    protected String[] token = new String[12]; //used to identify player and match
    protected int index; //used to keep message in order (the socket will discard messages too old)
    protected MessageType type; //used to identify the type of message

    public void send(){}
    public void resolve(Controller controller){}
}

class MessagePing extends Message{
    private String[] Serialized = new String[32];

    public void send(){
        this.type = MessageType.PING;
    }
}

class MessageLeaderActivation extends Message{
    protected int position;

    public MessageLeaderActivation(int position){
        this.position = position;
        this.type = MessageType.LeaderActivation;
    }

    public void resolve(Controller controller){
        controller.activateLeader(this.position);
    }

}

class MessageTakeResources extends Message{
    private boolean isRow;
    protected int position;

    public MessageTakeResources(boolean isRow, int position){
        this.position = position;
        this.isRow = isRow;
        this.type = MessageType.TakeResources;
    }

    public void resolve(Controller controller) {
        controller.takeResources(this.isRow, this.position);
    }
}

class MessageBuyDevCard extends Message{
    private Level level;
    private CardColor color;

    public MessageBuyDevCard(Level level, CardColor color){
        this.level = level;
        this.color = color;
        this.type = MessageType.BuyDevCard;
    }

    public void resolve(Controller controller){ controller.buyDevCard(this.level, this.color);}
}

class MessageTryDepotConfiguration extends Message{
    private Resource[] input = new Resource[10];

    public MessageTryDepotConfiguration(Resource[] input){
        System.arraycopy(input, 0, this.input, 0, input.length);
        this.type = MessageType.TryDepotConfiguration;
    }

    public void resolve(Controller controller){ controller.tryDepotConfiguration(this.input);}
}

class MessageProduce extends Message{
    private boolean[] activated = new boolean[6];

    public MessageProduce(boolean[] activated){
        System.arraycopy(activated, 0, this.activated, 0 ,activated.length);
        this.type = MessageType.Produce;
    }

    public void resolve(Controller controller){ controller.produce(this.activated);}

}

class MessageSetResource extends Message{
    private Resource resource;
    private int position;

    public MessageSetResource(Resource resource, int position){
        this.resource = resource;
        this.position = position;
        this.type = MessageType.SetResource;
    }

    public void resolve(Controller controller){controller.setResource(position, resource);}
}