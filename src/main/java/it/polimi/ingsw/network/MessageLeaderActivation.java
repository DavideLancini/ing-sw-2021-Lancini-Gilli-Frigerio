package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;

public class MessageLeaderActivation extends Message {
    protected int position;

    public MessageLeaderActivation(int position) {
        this.position = position;
        this.type = MessageType.LeaderActivation;
    }

    public void resolve(Controller controller) {
        controller.activateLeader(this.position);
    }

}
