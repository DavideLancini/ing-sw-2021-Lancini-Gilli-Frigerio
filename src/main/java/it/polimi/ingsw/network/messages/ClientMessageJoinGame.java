package it.polimi.ingsw.network.messages;

public class ClientMessageJoinGame extends ClientMessage {
    public String playerId;
    public ClientMessageJoinGame(String playerId){
        this.type=MessageType.JoinGame;
        this.playerId=playerId;
    }

    public String joinGame() {
        System.out.println("im joining the game");
        return this.playerId;
    }
}
