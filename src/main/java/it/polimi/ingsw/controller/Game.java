package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.PlayerBoard;

public class Game {
    private PlayerBoard[] pbs;
    /**
     * Game constructor
     * @param playerBoards all the players playing
     */
    public Game(PlayerBoard[] playerBoards){
        this.setPbs(playerBoards);
        switch (playerBoards.length ) {
            case 1: SinglePlayer(playerBoards);
            case 2: TwoPlayers(playerBoards);
            case 3: ThreePlayers(playerBoards);
            case 4: FourPlayers(playerBoards);
        }
    }
    private void SinglePlayer(PlayerBoard[] playerBoards){
        //singlePlayer
    }
    private void TwoPlayers(PlayerBoard[] playerBoards){
        while (true) {
            //start turn player1
            //wait endturn
            //check endgame
            //start turn player2
            //wait endturn
            //check endgame
        }
    }
    private void ThreePlayers(PlayerBoard[] playerBoards){
        while (true) {//start turn player1
            //wait endturn
            //check endgame
            //start turn player2
            //wait endturn
            //check endgame
            //start turn player3
            //wait endturn
            //check endgame
        }
    }

    private void FourPlayers(PlayerBoard[] playerBoards){
        while (true) {//start turn player1
            //wait endturn
            //check endgame
            //start turn player2
            //wait endturn
            //check endgame
            //start turn player3
            //wait endturn
            //check endgame
            //start turn player4
            //wait endturn
            //check endgame
        }
    }

    public PlayerBoard[] getPbs() {
        return pbs;
    }

    public void setPbs(PlayerBoard[] pbs) {
        this.pbs = pbs;
    }
}

