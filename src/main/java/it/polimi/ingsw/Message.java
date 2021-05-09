package it.polimi.ingsw;

import it.polimi.ingsw.model.*;

import java.util.Collection;

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

class MessageTryDepotConfiguration extends Message {
    private Resource[] input = {null, null, null, null, null, null, null, null, null, null};

    public MessageTryDepotConfiguration(Resource[] input){
        System.arraycopy(input, 0, this.input, 0, input.length);
    }

    public void resolve(Controller controller){ controller.tryDepotConfiguration(this.input);}
}