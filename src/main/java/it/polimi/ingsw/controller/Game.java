package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.singlePlayer.*;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.EndTurnException;
import it.polimi.ingsw.network.messages.ServerMessageOK;
import it.polimi.ingsw.network.messages.ServerMessageView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;

public class Game {
    private Player[] players;
    public DevCardBoard devCardBoard;
    public LeaderCardDeck leaderCardDeck;
    public Market market;
    private ActionPile actionPile;

    /**
     * Game constructor for single player
     * @param player single Player information
     */
    public Game(Player player) throws Exception {
        Server.logger.info("Game Created");
        player.net.send(new ServerMessageOK());
        setupGame();
        Server.logger.finest("Setup OK");
        setPlayers(new Player[]{player});

        System.out.println("Starting Solo Game");

        Server.logger.info("OK5");

        player.drawLeaderCards(leaderCardDeck.draw4());
        player.playerBoard = new PlayerBoard();
        player.setController(new Controller(player.net, player.playerBoard, this.devCardBoard, this.market));

        Server.logger.info("OK6");
        player.receiveLeaders();
        Server.logger.info("OK7");
        startSoloGame();
    }



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
     * starts a singlePlayer
     */
    private void startSoloGame(){
        boolean endGame=false;
        PcPlayerBoard pcPlayerBoard=new PcPlayerBoard();
        Server.logger.info("Game actually started");
        do {
            if(checkEndGame()) break;
            boolean done = false;
            boolean action = false;
            while(!done){
                try {
                        showAllGame(players[0]);
                        action = players[0].turn(action);
                        Server.logger.info("Setting action to "+action);
                }
                catch(EndTurnException | DisconnectedException e){done = true;}
            }
            endGame=pcPlayerBoard.turn(devCardBoard,players[0].net);
            if(pcPlayerBoard.getDarkFaith()>=20)
                endGame=true;
        }
        while (!endGame);
    }

    /**
     * starts a multiplayer game
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
    }

    private void createMarket() throws FileNotFoundException {
        Marble[] marbles= new Gson().fromJson(new FileReader("src/main/resources/Marbles.json"),Marble[].class);
        this.market = new Market(marbles);

    }

}

