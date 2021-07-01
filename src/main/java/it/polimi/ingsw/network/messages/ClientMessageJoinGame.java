package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.view.Log;

/**
 * Class ClientMessageJoinGame
 * @author Group 12
 */
public class ClientMessageJoinGame extends ClientMessage {
    private final String playerId;
    private final int gameMode;

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
        Log.logger.info(playerId+" is joining a game");
        return this.playerId;
    }

    public int getGameMode(){
        return gameMode;
    }
}
