package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.ServerMessage;

public class ServerMessageError extends ServerMessage {
    private String error;

    public ServerMessageError(String error) {
        this.error = error;
        this.type = ServerMessageType.Error;
    }

    public String getError() {
        return error;
    }
}
