package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.EndTurnException;
import it.polimi.ingsw.network.messages.ServerMessageOK;
import it.polimi.ingsw.network.messages.ServerMessageView;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Game {
    private Player[] players;
    public DevCardBoard devCardBoard;
    public LeaderCardDeck leaderCardDeck;
    public Market market;

    /**
     * Game constructor
     * @param players Players playing the game
     */
    public Game(Player[] players) throws Exception {
        Server.logger.info("Game Created");

        for(Player each: players) {
            each.net.send(new ServerMessageOK());
        }

        setupGame();
        Server.logger.finest("Setup OK");
        setPlayers(players);

        System.out.println("Starting Game with "+players.length+" players...");

        Server.logger.info("OK5");
        for(Player each: players) {
            each.drawLeaderCards(leaderCardDeck.draw4());
            each.playerBoard = new PlayerBoard();
            each.setController(new Controller(each.net, each.playerBoard, this.devCardBoard, this.market));
        }
        Server.logger.info("OK6");

        for(Player each: players){
            each.receiveLeaders();
        }
        Server.logger.info("OK7");


        if(players.length>=2)players[1].secondPlayer();
        if(players.length>=3)players[2].thirdPlayer();
        if(players.length>=4)players[3].fourthPlayer();
        if(players.length>=5)throw new Exception("Number of players out of range");

        startGame();
    }


    /**
     * starts a four player game
     *
     */
    private void startGame(){
        Server.logger.info("Game actually started");
        do {
            for(Player each : this.players) {
                boolean done = false;
                boolean action = false;
                while(!done){
                    try {
                        showAllGame(each);
                        action = each.turn(action);
                        Server.logger.info("Setting action to "+action);
                    }
                    catch(EndTurnException | DisconnectedException e){done = true;}
                }
            }
        }
        while (!checkEndGame());
    }

    private void showAllGame(Player currentPlayer) throws DisconnectedException {
        showPlayersBoards(currentPlayer);
        currentPlayer.net.send(new ServerMessageView(devCardBoard.topView()));
        currentPlayer.net.send(new ServerMessageView(currentPlayer.playerBoard.playerBoardView("YOU")));
        currentPlayer.net.send(new ServerMessageView(showLeaderCards(currentPlayer)));
        currentPlayer.net.send(new ServerMessageView(market.view()));
    }

    private String showLeaderCards(Player currentPlayer) {
        String string="";
        LeaderCard[] tempLeaders={currentPlayer.playerBoard.getLeaderCard(0),currentPlayer.playerBoard.getLeaderCard(1)};
        string=string.concat(tempLeaders[0].view());
        if(tempLeaders[0].getIsActive())
            string=string.concat("is active");
        string=string.concat(tempLeaders[1].view());
        if(tempLeaders[0].getIsActive())
            string=string.concat("is active");
        return string;
    }

    private void showPlayersBoards(Player currentPlayer) throws DisconnectedException {
        for (Player player : players) {
            if(player!=currentPlayer) currentPlayer.net.send(new ServerMessageView(player.playerBoard.playerBoardView(player.playerId)));
        }
    }


    public void setPlayers(Player[] players) {
        this.players= players;
    }

    private boolean checkEndGame(){
        for(Player each : this.players) {
            if(each.playerBoard.getFaith() >= 20 || each.playerBoard.getDevCardsNumber() >= 7) return true;
        }
        //TODO: Testing
        return false;
    }


    /**
     * Setup of game's elements
     * @throws FileNotFoundException if file json is not found
     */
    private void setupGame() throws FileNotFoundException {
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

    }

    private void createMarket() throws FileNotFoundException {
        Marble[] marbles= new Gson().fromJson(new FileReader("src/main/resources/Marbles.json"),Marble[].class);
        this.market = new Market(marbles);

    }

}

