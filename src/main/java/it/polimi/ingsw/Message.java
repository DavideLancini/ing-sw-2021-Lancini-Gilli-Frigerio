package it.polimi.ingsw;

import it.polimi.ingsw.model.*;


public abstract class Message {
    public void resolve(Controller controller){}
}

class MessageLeaderActivation extends Message{
    private int position;

    public MessageLeaderActivation(int position){
        this.position = position;
    }

    public void resolve(Controller controller){
        controller.activateLeader(this.position);
    }

}

class MessageTakeResources extends Message{
    private boolean isRow;
    private int position;

    public MessageTakeResources(boolean isRow, int position){
        this.position = position;
        this.isRow = isRow;
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
    }

    public void resolve(Controller controller){ controller.buyDevCard(this.level, this.color);}
}

class MessageTryDepotConfiguration extends Message{
    private Resource[] input = new Resource[10];

    public MessageTryDepotConfiguration(Resource[] input){
        System.arraycopy(input, 0, this.input, 0, input.length);
    }

    public void resolve(Controller controller){ controller.tryDepotConfiguration(this.input);}
}

class MessageProduce extends Message{
    private boolean[] activated = new boolean[6];

    public MessageProduce(boolean[] activated){ System.arraycopy(activated, 0, this.activated, 0 ,activated.length);}

    public void resolve(Controller controller){ controller.produce(this.activated);}

}

class MessageSetResource extends Message{
    private Resource resource;
    private int position;

    public MessageSetResource(Resource resource, int position){
        this.resource = resource;
        this.position = position;
    }

    public void resolve(Controller controller){controller.setResource(position, resource);}
}