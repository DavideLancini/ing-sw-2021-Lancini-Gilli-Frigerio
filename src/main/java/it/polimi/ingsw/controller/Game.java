package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Game {
    public Player[] players;
    public DevCardBoard devCardBoard;
    public LeaderCardDeck leaderCardDeck;
    public Market market;
    private boolean endGame=false;
    /**
     * Game constructor
     * @param players
     */
    public Game(Player[] players) throws Exception {
        startGame();
        setPlayers(players);
        switch (players.length) {
            case 1:
                System.out.println("Starting SinglePlayer...");
                singlePlayer(players);
                break;
            case 2:
                System.out.println("Starting TwoPlayers...");
                twoPlayers(players);
                break;
            case 3:
                System.out.println("Starting ThreePlayers...");
                threePlayers(players);
                break;
            case 4:
                System.out.println("Starting FourPlayers...");
                fourPlayers(players);
                break;
            default:
                System.out.println("not in range");
                break;
        }
    }

    /**
     * starts single player Match
     * @param players id of player
     */
    private void singlePlayer(Player[] players){
        while (!endGame) {
            endGame = players[0].turn();
        }
    }

    /**
     * starts a two player game
     * @param players id of players
     * @throws Exception deposit errors
     */
    private void twoPlayers(Player[] players) throws Exception {
        players[1].secondPlayer();
        int j=0;
        while (!endGame){
        endGame=players[j].turn();
        //start turn player
        // wait endTurn
        //check endGame
        if(j==1)
            j=0;
        else
            j++;
        }

    }
    /**
     * starts a three player game
     * @param players id of players
     * @throws Exception deposit errors
     */
    private void threePlayers(Player[] players) throws Exception {
        players[1].secondPlayer();
        players[2].thirdPlayer();
        int j=0;
        while (!endGame){
        endGame=players[j].turn();
        if(j==2)
            j=0;
        else
            j++;
        }
    }
    /**
     * starts a four player game
     * @param players id of players
     * @throws Exception deposit errors
     */
    private void fourPlayers(Player[] players) throws Exception {
        players[1].secondPlayer();
        players[2].thirdPlayer();
        players[3].fourthPlayer();
        int j = 0;
        while (!endGame){
        endGame=players[j].turn();
        if (j== 3)
            j = 0;
        else
            j++;
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players= players;
    }
    /**
     * StartGame constructor
     */
    private void startGame(/*players*/) throws FileNotFoundException {
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

