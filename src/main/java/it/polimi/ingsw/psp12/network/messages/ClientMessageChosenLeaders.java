package it.polimi.ingsw.psp12.network.messages;
/**
 * Class ClientMessageChosenLeaders
 * @author Group 12
 */
public class ClientMessageChosenLeaders extends ClientMessage{
    private final int[] positions = new int[2];

    /**
     * constructor
     * @param a identification number of first leaderCard selected
     * @param b identification number of second leaderCard selected
     */
    public ClientMessageChosenLeaders(int a, int b){
        this.type = MessageType.ChosenLeaders;
        this.positions[0] = a;
        this.positions[1] = b;
    }

    public int[] getPositions(){return this.positions;}

}
