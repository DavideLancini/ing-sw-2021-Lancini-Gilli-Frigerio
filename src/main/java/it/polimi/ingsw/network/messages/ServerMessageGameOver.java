package it.polimi.ingsw.network.messages;

public class ServerMessageGameOver extends ServerMessage{
    public ServerMessageGameOver(){
        this.type = MessageType.GameOver;
    }
}
