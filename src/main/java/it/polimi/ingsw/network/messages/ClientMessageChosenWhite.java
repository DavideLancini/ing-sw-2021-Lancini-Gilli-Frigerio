package it.polimi.ingsw.network.messages;

public class ClientMessageChosenWhite extends ClientMessage{
    private final boolean position;

    public ClientMessageChosenWhite(boolean pos){
        this.type = MessageType.ChosenWhite;
        this.position = pos;
    }

    public boolean getPosition(){
        return position;
    }
}
