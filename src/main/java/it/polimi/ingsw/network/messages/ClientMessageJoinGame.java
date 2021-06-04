package it.polimi.ingsw.network.messages;

public class ClientMessageJoinGame extends ClientMessage {
    private String playerId;
    private int gameMode;

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
