package it.polimi.ingsw.psp12.network.messages;
/**
 * Class ServerMessageGameOver
 * @author Group 12
 */
public class ServerMessageGameOver extends ServerMessage{
    public ServerMessageGameOver(){
        this.type = MessageType.GameOver;
    }
}
