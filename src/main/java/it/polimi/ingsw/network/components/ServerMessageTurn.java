package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.ServerMessage;
import it.polimi.ingsw.network.components.ServerMessageType;

public class ServerMessageTurn extends ServerMessage {
    //Track if in this turn an action has already been performed
    private boolean mainAction;

    public ServerMessageTurn(boolean mainAction) {
        this.type = ServerMessageType.Turn;
        this.mainAction = mainAction;
    }

    public boolean getAction(){return this.mainAction;}

}
