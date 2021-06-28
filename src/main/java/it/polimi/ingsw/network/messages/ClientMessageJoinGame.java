package it.polimi.ingsw.network.messages;
/**
 * Class ClientMessageJoinGame
 * @author gruppo 12
 */
public class ClientMessageJoinGame extends ClientMessage {
    private String playerId;
    private int gameMode;

    /**
     * constructor
     * @param playerId Player Id
     * @param gameMode number of players
     */
    public ClientMessageJoinGame(String playerId, int gameMode){
        this.type=MessageType.JoinGame;
        this.playerId=playerId;
        this.gameMode=gameMode;
    }

    public String getPlayerId() {
        System.out.println("im joining the game");
        return this.playerId;
    }

    public int getGameMode(){
        return gameMode;
    }
}
