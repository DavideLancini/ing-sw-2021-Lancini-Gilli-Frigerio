package it.polimi.ingsw.psp12.network.messages;

import it.polimi.ingsw.psp12.controller.Controller;
import it.polimi.ingsw.psp12.network.DisconnectedException;
/**
 * Class ClientMessageLeaderActivation
 * @author Group 12
 */
public class ClientMessageLeaderActivation extends ClientMessage {
    public final int position;

    /**
     * constructor
     * @param position selected leader to activate (0=left, 1=right)
     */
    public ClientMessageLeaderActivation(int position) {
        this.position = position;
        this.type = MessageType.LeaderActivation;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        controller.activateLeader(this.position);
        return false;
    }

}
