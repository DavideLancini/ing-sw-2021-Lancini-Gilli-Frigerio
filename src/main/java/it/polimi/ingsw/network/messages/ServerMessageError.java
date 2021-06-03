package it.polimi.ingsw.network.messages;

public class ServerMessageError extends ServerMessage {
    private String error;

    public ServerMessageError(String error) {
        this.error = error;
        this.type = MessageType.Error;
    }

    public String getError() {
        return error;
    }
}
