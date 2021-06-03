package it.polimi.ingsw.network.messages;

public class ClientMessageChosenLeaders extends ClientMessage{
    private int[] positions = new int[2];

    public ClientMessageChosenLeaders(int a, int b){
        this.type = MessageType.ChosenLeaders;
        this.positions[0] = a;
        this.positions[1] = b;
    }

    public int[] getPositions(){return this.positions;}

}
