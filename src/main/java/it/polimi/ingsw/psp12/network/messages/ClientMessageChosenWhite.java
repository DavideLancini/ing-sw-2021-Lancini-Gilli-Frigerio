package it.polimi.ingsw.psp12.network.messages;
/**
 * Class ClientMessageChosenWhite
 * @author Group 12
 */
public class ClientMessageChosenWhite extends ClientMessage{
    private final boolean position;

    /**
     * constructor
     * @param pos true if user selected second active leaderTransform
     */
    public ClientMessageChosenWhite(boolean pos){
        this.type = MessageType.ChosenWhite;
        this.position = pos;
    }

    public boolean getPosition(){
        return position;
    }
}
