package it.polimi.ingsw.psp12.network.messages;
/**
 * Class ServerMessageError
 * @author Group 12
 */
public class ServerMessageError extends ServerMessage {
    private final String error;

    /**
     * constructor
     * @param error error description
     */
    public ServerMessageError(String error) {
        this.error = error;
        this.type = MessageType.Error;
    }

    public String getError() {
        return error;
    }
}
