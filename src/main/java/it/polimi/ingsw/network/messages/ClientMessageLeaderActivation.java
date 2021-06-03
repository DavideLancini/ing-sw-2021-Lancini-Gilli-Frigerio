package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;

public class ClientMessageLeaderActivation extends ClientMessage {
    public int position;

    public ClientMessageLeaderActivation(int position) {
        this.position = position;
        this.type = MessageType.LeaderActivation;
    }

    public boolean resolve(Controller controller) {
        controller.activateLeader(this.position);
        return false;
    }

}
