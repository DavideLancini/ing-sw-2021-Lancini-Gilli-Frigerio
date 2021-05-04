package it.polimi.ingsw;

public abstract class Message {
    public void resolve(Controller controller){}
}

class MessageLeaderActivation extends Message{
    private int position;

    public MessageLeaderActivation(int position){
        this.position = position;
    }

    public void resolve(BoardController controller){
        controller.ActivateLeader(this.position);
    }

}

class MessageTakeResources extends Message{
    private boolean isRow;
    private int position;

    public MessageTakeResources(boolean isRow, int position){
        this.position = position;
        this.isRow = isRow;
    }

    public void resolve(MarketController controller) {
        controller.takeResources(this.isRow, this.position);
    }
}