package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.ClientMessage;

public class ClientMessageCreateGame extends ClientMessage {
    private final int players;
    public Object createGame;

    public ClientMessageCreateGame(int players){
        this.type=MessageType.CreateGame;
        this.players=players;
    }

    public void CreateGame(){
        //TODO
    }
}
