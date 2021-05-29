package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.Message;

public class MessageLeaderActivation extends Message {
    public int position;

    public MessageLeaderActivation(int position) {
        this.position = position;
        this.type = MessageType.LeaderActivation;
    }

    public boolean resolve(Controller controller) {
        controller.activateLeader(this.position);
        return false;
    }

}
