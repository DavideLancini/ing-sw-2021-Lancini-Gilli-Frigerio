package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.messages.EndTurnException;
import it.polimi.ingsw.network.messages.ServerMessageOK;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

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
        Server.logger.info("Gioco Creato");

        for(Player each: players) {
            each.net.send(new ServerMessageOK());
        }

        setupGame();
        Server.logger.finest("Setup OK");
        setPlayers(players);
        /*Server.logger.info("OK1 ");
        if(players.length>=2)players[1].secondPlayer();
        Server.logger.info("OK2");
        if(players.length>=3)players[2].thirdPlayer();
        Server.logger.info("OK3");
        if(players.length>=4)players[3].fourthPlayer();
        Server.logger.info("OK4");*/
        if(players.length>=5)throw new Exception("Number of players out of range");
        System.out.println("Starting Game with "+players.length+" players...");

        Server.logger.info("OK5");
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
        Server.logger.info("OK1 ");
        createLeaderDeck();
        Server.logger.info("OK2 ");
        createMarket();
        Server.logger.info("OK3 ");
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
        Server.logger.info("OK3 ");
        LeaderTransform[] leaderTransformDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/TransformCard.json"),LeaderTransform[].class);
        Server.logger.info("OK4 ");
        LeaderProduction[] leaderProductionDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/ProductionCard.json"),LeaderProduction[].class);
        Server.logger.info("OK5 ");
        LeaderSale[] leaderSaleDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/SaleCard.json"), LeaderSale[].class);
        Server.logger.info("OK6 ");
        LeaderDepot[] leaderDepotDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/DepotCard.json"),LeaderDepot[].class);
        Server.logger.info("OK7 ");
        LeaderCard[] deck= new LeaderCard[16];
        Server.logger.info("OK8 ");
        System.arraycopy(leaderTransformDeck,0,deck,0,leaderTransformDeck.length);
        Server.logger.info("OK9 ");
        System.arraycopy(leaderProductionDeck,0,deck,4,leaderProductionDeck.length);
        Server.logger.info("OK10 ");
        System.arraycopy(leaderSaleDeck,0,deck,8,leaderSaleDeck.length);
        Server.logger.info("OK11 ");
        System.arraycopy(leaderDepotDeck,0,deck,12,leaderDepotDeck.length);
        Server.logger.info("OK12 ");
        this.leaderCardDeck = new LeaderCardDeck(deck);
        Server.logger.info("FINE");
        // for (LeaderCard leaderCard : deck){leaderCard.leaderCardView();}
    }

    private void createMarket() throws FileNotFoundException {
        Marble[] marbles= new Gson().fromJson(new FileReader("src/main/resources/Marbles.json"),Marble[].class);
        this.market = new Market(marbles);
        //Market.marketView();
    }

}

