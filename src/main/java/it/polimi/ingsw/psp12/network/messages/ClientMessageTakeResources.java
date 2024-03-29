package it.polimi.ingsw.psp12.network.messages;

import it.polimi.ingsw.psp12.controller.Controller;
import it.polimi.ingsw.psp12.network.DisconnectedException;
/**
 * Class ClientMessageLeaderActivation
 * @author Group 12
 */
public class ClientMessageTakeResources extends ClientMessage {
    private final boolean isRow;
    public final int position;

    /**
     * constructor
     * @param isRow true if user selected a row, false if user selected a column
     * @param position number of row/column
     */
    public ClientMessageTakeResources(boolean isRow, int position) {
        this.position = position;
        this.isRow = isRow;
        this.type = MessageType.TakeResources;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        return controller.takeResources(this.isRow, this.position);
    }
}
