package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.ServerMessage;

public class ServerMessageOK extends ServerMessage {

    public ServerMessageOK() {
        this.type = ServerMessageType.OK;
    }

}
