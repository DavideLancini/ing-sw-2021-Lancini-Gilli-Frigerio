package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.ServerMessage;

public class ServerMessageOK extends ServerMessage {

    public ServerMessageOK() {
        this.type = MessageType.OK;
    }

}
