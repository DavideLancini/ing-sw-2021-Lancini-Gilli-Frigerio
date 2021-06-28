package it.polimi.ingsw.network.messages;


/**
 * Class ClientMessageCreateGame
 * @author gruppo 12
 */
public class ClientMessageCreateGame extends ClientMessage {
    private final int players;
    public String playerId;

    /**
     * constructor
     * @param players number of players
     * @param playerId Player ID
     */
    public ClientMessageCreateGame(int players, String playerId){
        this.type=MessageType.CreateGame;
        this.players=players;
        this.playerId=playerId;
    }

    public String CreateGame(){
        System.out.println("im creating the game");
        return this.playerId;
        //TODO
    }
}
