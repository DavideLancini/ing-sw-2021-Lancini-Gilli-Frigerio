package it.polimi.ingsw.psp12.network.messages;
/**
 * Class ServerMessageAddResource
 * @author Group 12
 */
public class ServerMessageAddResource extends ServerMessage {
    public ServerMessageAddResource() {
        this.type = MessageType.AddResource;
    }

}
