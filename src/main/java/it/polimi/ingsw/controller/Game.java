package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.singlePlayer.*;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.EndTurnException;
import it.polimi.ingsw.network.messages.ServerMessageOK;
import it.polimi.ingsw.network.messages.ServerMessageView;
import it.polimi.ingsw.view.ViewHelper;
import it.polimi.ingsw.view.gui.GUIElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Arrays;


public class Game {
    private Player[] players;
    public DevCardBoard devCardBoard;
    public LeaderCardDeck leaderCardDeck;
    public Market market;
    private Pope pope = Pope.FIRST;

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
        player.setController(new Controller(player.net, player.playerBoard, this.devCardBoard, this.market, this));

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
            each.setController(new Controller(each.net, each.playerBoard, this.devCardBoard, this.market, this));
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


    private void startSoloGame() throws DisconnectedException {
        PcPlayerBoard pc= new PcPlayerBoard(this.devCardBoard);
        Server.logger.info("Solo game actually started");

        do{
            boolean done = false;
            boolean action = false;
            while(!done){
                try {
                    showAllGame(players[0]);
                    action = players[0].turn(action);
                    Server.logger.info("Setting action to "+action);
                }
                catch(EndTurnException e){done = true;}
            }
            if(checkEndGame()) {
                players[0].net.send(new ServerMessageView("The Game is now over! You have won."));
            }
            String turn;
            try{turn = pc.turn();}
            catch (EndGameException e){
                players[0].net.send(new ServerMessageView(e.getMessage()));
                players[0].net.send(new ServerMessageView("The Game is now over! You have lost."));
                return;
            }
            players[0].net.send(new ServerMessageView(turn));
        }
        while(true);
    }






    /**
     * starts a multiplayer game
     */
    private void startGame(){
        Server.logger.info("Game actually started");
        boolean endGame = false;
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
                checkPope();
                if(!endGame) endGame = checkEndGame();
            }
        }
        while (!endGame);
        finalSummary();
    }

    private void showAllGame(Player currentPlayer) throws DisconnectedException {
        showPlayersBoards(currentPlayer);
        currentPlayer.net.send(new ServerMessageView(devCardBoard.topView(), Serializer.serialize(devCardBoard), GUIElement.DevBoard));
        currentPlayer.net.send(new ServerMessageView(currentPlayer.playerBoard.playerBoardView("YOU"), Serializer.serialize(currentPlayer.playerBoard), GUIElement.PB));
        currentPlayer.net.send(new ServerMessageView(showLeaderCards(currentPlayer)));
        currentPlayer.net.send(new ServerMessageView(market.view(), Serializer.serialize(market), GUIElement.Market));
    }

    private String showLeaderCards(Player currentPlayer) {

        LeaderCard[] tempLeaders={currentPlayer.playerBoard.getLeaderCard(0),currentPlayer.playerBoard.getLeaderCard(1)};
        String[] sleaders = new String[2];
        for (int i = 0; i<2; i++) {
            String string="Leader "+(i+1)+". \t\n";
            if (tempLeaders[i] == null) string = string.concat(
                    "══════════════╗\n"+
                    "   \\ /\t\t\n" +
                    "    X\t\t\n" +
                    "   / \\\t\t\n")+
                    "══════════════╝\n";
            else {
                string = string.concat(tempLeaders[i].view());
                string = string.concat(tempLeaders[i].getIsActive() ? "\tis active\t" : "\t");
            }
            sleaders[i] = string;
        }

        return ViewHelper.displayS2S(sleaders);
    }

    private void showPlayersBoards(Player currentPlayer) throws DisconnectedException {
        for (Player player : players) {
            if(player!=currentPlayer) {

                currentPlayer.net.send(new ServerMessageView(player.playerBoard.playerBoardView(player.playerId) , Serializer.serialize(player.playerBoard), GUIElement.OtherPB));

                String[] cards = new String[2];

                for(int i = 0; i<2; i++) cards[i] = player.playerBoard.getLeaderCard(i).getIsActive() ? player.playerBoard.getLeaderCard(i).view() :
                        ("══════════════╗\n"+
                         " ████████████  \n" +
                         " ████████████  \n" +
                         " ████████████  \n"+
                         "══════════════╝\n");

                currentPlayer.net.send(new ServerMessageView("Leader cards:\n"+ViewHelper.displayS2S(cards)));
            }
        }
    }


    public void setPlayers(Player[] players) {
        this.players= players;
    }

    private boolean checkEndGame(){
        for(Player each : this.players) {
            if(each.playerBoard.getFaith() >= 24 || each.playerBoard.getDevCardsNumber() >= 7) {
                for(Player every : this.players){
                    try{every.net.send(new ServerMessageView("The Game will be over at the end of this round!"));}
                    catch (DisconnectedException ignored){}
                }

                return true;
            }
        }
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

    private void finalSummary(){
        int[] results = new int[this.players.length];

        for (int i = 0; i< this.players.length; i++) {
            results[i] = this.players[i].playerBoard.getTotalVP();
        }
        int[] sortedresults = new int[results.length];
        System.arraycopy(sortedresults, 0, results, 0, results.length);
        Arrays.sort(sortedresults);

        Player winner = null;
        String string = "Game is over!\nThese are the final rankings:\n\n";
        for (int j = 1; j <= this.players.length; j++) {

            for (int i = 0; i< this.players.length; i++) {
                if(sortedresults[j-1] == results[i]) {
                    string = string.concat(j+". "+this.players[i].playerId+": "+sortedresults[j-1]+"\n");
                    results[i] = -1;
                    if(j == 1) winner = this.players[i];
                    break;
                }
            }
        }

        for(Player each : this.players){
            try {
                each.net.send(new ServerMessageView(string));
                each.net.send(new ServerMessageView(each.equals(winner) ? "You Won!" : "You Lost!"));
            }
            catch (DisconnectedException ignored){}
        }
    }


    public void discardsToFaith(PlayerBoard pb, int amount) {
        for (Player player : this.players) {
            if (player.playerBoard != pb) player.playerBoard.addFaith(amount);
        }
    }

    private void checkPope(){
        String trigger = null;
        for(Player each: players)
            if (each.playerBoard.getFaith() >= pope.value) {
                trigger = each.playerId;
                break;
            }
        if(trigger == null)return;

        for (Player each: players) {
            try {
                if (each.playerBoard.getFaith() >= pope.min) {
                    each.playerBoard.addPope(pope.vp);
                    each.net.send(new ServerMessageView(trigger.equals(each.playerId) ? "You" : trigger + " activated a new Vatican Report. You receive "+ pope.vp+" additional Victory Points."));
                } else
                    each.net.send(new ServerMessageView(trigger + " activated a new Vatican Report. Unfortunately you don't receive any additional Victory Points."));
            }
            catch (DisconnectedException ignored){}
        }
        switch (pope){
            case FIRST: pope = Pope.SECOND;break;
            case SECOND: pope = Pope.THIRD;break;
            case THIRD: pope = Pope.DONE;break;
            default: throw new IllegalStateException();
        }

        for (Player each : players) try{each.net.send(new ServerMessageView("Next Report will trigger at "+pope.value+" faith."));}catch (DisconnectedException ignored){}

    }

}

