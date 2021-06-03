package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.messages.EndTurnException;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Game {
    private Player[] players;
    public DevCardBoard devCardBoard;
    public LeaderCardDeck leaderCardDeck;
    public Market market;
    private boolean endGame=false;
    /**
     * Game constructor
     * @param players
     */
    public Game(Player[] players) throws Exception {
        setupGame();
        setPlayers(players);
        if(players.length>=2)players[1].secondPlayer();
        if(players.length>=3)players[2].thirdPlayer();
        if(players.length>=4)players[3].fourthPlayer();
        if(players.length>=5)throw new Exception("Number of players out of range");
        System.out.println("Starting Game with "+players.length+" players...");

        for(Player each: players) {
            each.drawLeaderCards(leaderCardDeck.draw4());
            each.playerBoard = new PlayerBoard();
            each.setController(new Controller(each.playerBoard, this.devCardBoard, this.market));
        }


        startGame();
    }


    /**
     * starts a four player game
     *
     */
    private void startGame() {

        do {
            for(Player each : this.players) {
                boolean done = false;
                boolean action = false;
                while(!done){
                    try {
                        action = each.turn(false);
                    }
                    catch(EndTurnException e){done = true;}
                }
            }
        }
        while (!checkEndGame());
    }



    public Player[] getPlayers() {
        return this.players;
    }

    public void setPlayers(Player[] players) {
        this.players= players;
    }

    private boolean checkEndGame(){
        //TODO
        return true;
    }


    /**
     * StartGame constructor
     */
    private void setupGame(/*players*/) throws FileNotFoundException {
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
        this.devCardBoard = new DevCardBoard(board);
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
        this.leaderCardDeck = new LeaderCardDeck(deck);
        // for (LeaderCard leaderCard : deck){leaderCard.leaderCardView();}
    }

    private void createMarket() throws FileNotFoundException {
        Marble[] marbles= new Gson().fromJson(new FileReader("src/main/resources/Marbles.json"),Marble[].class);
        this.market = new Market(marbles);
        //Market.marketView();
    }

}

