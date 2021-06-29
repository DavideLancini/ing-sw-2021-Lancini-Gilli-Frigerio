package it.polimi.ingsw.network.messages;
/**
 * Class ServerMessageTurn
 * @author Group 12
 */
public class ServerMessageTurn extends ServerMessage {
    //Track if in this turn an action has already been performed
    private boolean mainAction;

    /**
     * constructor
     * @param mainAction true if in this turn a main action has already been performed
     */
    public ServerMessageTurn(boolean mainAction) {
        this.type = MessageType.Turn;
        this.mainAction = mainAction;
    }
    public boolean getAction(){return this.mainAction;}
}