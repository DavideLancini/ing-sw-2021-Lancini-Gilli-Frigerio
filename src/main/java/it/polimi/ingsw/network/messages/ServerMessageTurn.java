package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.ServerMessage;

public class ServerMessageTurn extends ServerMessage {
    //Track if in this turn an action has already been performed
    private boolean mainAction;

    public ServerMessageTurn(boolean mainAction) {
        this.type = MessageType.Turn;
        this.mainAction = mainAction;
    }

    public boolean getAction(){return this.mainAction;}

}
