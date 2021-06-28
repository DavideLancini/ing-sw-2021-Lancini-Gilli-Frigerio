package it.polimi.ingsw.network.messages;
/**
 * Class ServerMessageAddResource
 * @author gruppo 12
 */
public class ServerMessageAddResource extends ServerMessage {
    public ServerMessageAddResource() {
        this.type = MessageType.AddResource;
    }

}
