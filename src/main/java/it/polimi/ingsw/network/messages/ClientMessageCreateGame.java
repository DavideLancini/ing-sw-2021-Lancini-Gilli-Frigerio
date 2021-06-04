package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Player;

public class ClientMessageCreateGame extends ClientMessage {
    private final int players;
    public String playerId;

    public ClientMessageCreateGame(int players, String s){
        this.type=MessageType.CreateGame;
        this.players=players;
        this.playerId=s;
    }

    public String CreateGame(){
        System.out.println("im creating the game");
        return this.playerId;
        //TODO
    }
}
