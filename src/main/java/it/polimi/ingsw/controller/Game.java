package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Game {
    public PlayerBoard[] pbs;
    public DevCardBoard devCardBoard;
    public LeaderCardDeck leaderCardDeck;
    public Market market;
    public int i=0;
    /**
     * Game constructor
     * @param playerBoards all the players playing
     */
    public Game(PlayerBoard[] playerBoards) throws FileNotFoundException, IllegalStateException {
        StartGame();
        setPbs(playerBoards);
        switch (playerBoards.length ) {
            case 1: SinglePlayer(playerBoards);
            case 2: TwoPlayers(playerBoards);
            case 3: ThreePlayers(playerBoards);
            case 4: FourPlayers(playerBoards);
        }
    }
    private void SinglePlayer(PlayerBoard[] playerBoards){
        i++;
        //singlePlayer
    }
    private void TwoPlayers(PlayerBoard[] playerBoards) throws FileNotFoundException {
        //start turn player1
        //wait endturn
        //check endgame
        //start turn player2
        //wait endturn
        //check endgame
    }
    private void ThreePlayers(PlayerBoard[] playerBoards){
        //start turn player1
        //wait endturn
        //check endgame
        //start turn player2
        //wait endturn
        //check endgame
        //start turn player3
        //wait endturn
        //check endgame
    }

    private void FourPlayers(PlayerBoard[] playerBoards){
        //start turn player1
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

    public PlayerBoard[] getPbs() {
        return pbs;
    }

    public void setPbs(PlayerBoard[] pbs) {
        this.pbs = pbs;
    }
    /**
     * StartGame constructor
     */
    private void StartGame(/*players*/) throws FileNotFoundException {
        createDevCardBoard();
        createLeaderDeck();
        createMarket();
    }
    /**
     * creates DevCardBoard
     * @throws FileNotFoundException if file.json is not found
     */
    private void createDevCardBoard() throws FileNotFoundException {
        DevCardDeck[][] board= new Gson().fromJson(new FileReader("src/main/resources/DevCardBOARD.json"),DevCardDeck[][].class);
        devCardBoard = new DevCardBoard(board);
        //DevCardBoard.topView(DevCardBoard.getTop(DevCardBoard.getBoard()));
    }

    /**
     * creates LeaderCardDeck
     * @throws FileNotFoundException if files.json is not found
     */
    private void createLeaderDeck() throws FileNotFoundException {
        LeaderTransform[] leaderTransformDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/TransformCard.json"),LeaderTransform[].class);
        LeaderProduction[] leaderProductionDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/ProductionCard.json"),LeaderProduction[].class);
        LeaderSale[] leaderSaleDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/SaleCard.json"), LeaderSale[].class);
        LeaderDepot[] leaderDepotDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/DepotCard.json"),LeaderDepot[].class);
        LeaderCard[] deck= new LeaderCard[16];
        System.arraycopy(leaderTransformDeck,0,deck,0,leaderTransformDeck.length);
        System.arraycopy(leaderProductionDeck,0,deck,4,leaderProductionDeck.length);
        System.arraycopy(leaderSaleDeck,0,deck,8,leaderSaleDeck.length);
        System.arraycopy(leaderDepotDeck,0,deck,12,leaderDepotDeck.length);
        leaderCardDeck = new LeaderCardDeck(deck);
        // for (LeaderCard leaderCard : deck){leaderCard.leaderCardView();}
    }

    private void createMarket() throws FileNotFoundException {
        Marble[] marbles= new Gson().fromJson(new FileReader("src/main/resources/Marbles.json"),Marble[].class);
        market = new Market(marbles);
        //Market.marketView();
    }

}

