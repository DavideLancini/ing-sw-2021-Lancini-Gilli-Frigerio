package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.DisconnectedException;

public class ClientMessageLeaderActivation extends ClientMessage {
    public int position;

    public ClientMessageLeaderActivation(int position) {
        this.position = position;
        this.type = MessageType.LeaderActivation;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        controller.activateLeader(this.position);
        return false;
    }

}
