package it.polimi.ingsw.psp12.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.psp12.model.*;
import it.polimi.ingsw.psp12.model.singlePlayer.PcPlayerBoard;
import it.polimi.ingsw.psp12.network.DisconnectedException;
import it.polimi.ingsw.psp12.network.components.Serializer;
import it.polimi.ingsw.psp12.network.messages.*;
import it.polimi.ingsw.psp12.view.Log;
import it.polimi.ingsw.psp12.view.ViewHelper;
import it.polimi.ingsw.psp12.view.gui.GUIElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Class game
 * @author Group 12
 */
public class Game {
    private Player[] players;
    public DevCardBoard devCardBoard;
    public LeaderCardDeck leaderCardDeck;
    public Market market;
    private Pope pope = Pope.FIRST;
    private PcPlayerBoard pc;
    /**
     * Game constructor for single player
     * @param player single Player information
     */
    public Game(Player player) {
        Log.logger.info("Game Created");
        try {
            player.net.send(new ServerMessageOK());
            Log.logger.finest("TEST OK");
            setupGame();
            Log.logger.finest("Setup OK");
            setPlayers(new Player[]{player});

            System.out.println("Starting Solo Game");

            Log.logger.info("OK5");

            player.drawLeaderCards(leaderCardDeck.draw4());
            player.playerBoard = new PlayerBoard();
            player.setController(new Controller(player.net, player.playerBoard, this.devCardBoard, this.market, this));

            Log.logger.info("OK6");
            player.receiveLeaders();
            Log.logger.info("OK7");
            startSoloGame();
        }
        catch (DisconnectedException | FileNotFoundException e){
            try {
                player.net.send(new ServerMessageError("There has been an error while starting the game, please retry."));
                player.net.send(new ServerMessageGameOver());
                Log.logger.warning("Game aborted during creation: "+e.getMessage());
            } catch (DisconnectedException ignored) {
            }
        }
    }



    /**
     * Game constructor
     * @param players Players playing the game
     */
    public Game(Player[] players) {
        Log.logger.info("Game Created");

        try {
            for (Player each : players) {
                each.net.send(new ServerMessageOK());
            }

            setupGame();
            Log.logger.finest("Setup OK");
            setPlayers(players);

            System.out.println("Starting Game with " + players.length + " players...");

            Log.logger.info("OK5");
            for (Player each : players) {
                each.drawLeaderCards(leaderCardDeck.draw4());
                each.playerBoard = new PlayerBoard();
                each.setController(new Controller(each.net, each.playerBoard, this.devCardBoard, this.market, this));
            }
            Log.logger.info("OK6");

            for (Player each : players) {
                each.receiveLeaders();
            }
            Log.logger.info("OK7");


            if (players.length >= 2) players[1].secondPlayer();
            if (players.length >= 3) players[2].thirdPlayer();
            if (players.length >= 4) players[3].fourthPlayer();
        }
        catch (DisconnectedException | FileNotFoundException e){
            for (Player each : players) {
                try {
                    each.net.send(new ServerMessageError("There has been an error while starting the game, please retry."));
                    each.net.send(new ServerMessageGameOver());
                    Log.logger.warning("Game aborted during creation: "+e.getMessage());
                } catch (DisconnectedException ignored) {
                }
            }
        }

        startGame();
    }

    /**
     * manages single player games
     * @throws DisconnectedException if the is a disconnection during the game
     */
    private void startSoloGame() throws DisconnectedException {
        this.pc = new PcPlayerBoard(this.devCardBoard);
        Log.logger.info("Solo game actually started");

        do {
            boolean done = false;
            boolean action = false;
            while (!done) {
                try {
                    showAllGame(players[0]);
                    if (!action) action = players[0].turn(false);
                    else players[0].turn(true);
                    Log.logger.info("Setting action to " + action);
                } catch (EndTurnException e) {
                    done = true;
                }
            }

            //CheckPope for player
            checkPope();


            if (checkEndGame()) {
                players[0].net.send(new ServerMessageView("The Game is now over! You have won.\n Total points: "
                        + players[0].playerBoard.getTotalVP(), null, GUIElement.Pure));
                players[0].net.send(new ServerMessageGameOver());

                return;
            }
            String turn;
            try {
                turn = pc.turn();
            } catch (EndGameException e) {
                players[0].net.send(new ServerMessageView(e.getMessage(), null, GUIElement.Solo));
                players[0].net.send(new ServerMessageView("The Game is now over! You have lost.\n Total points: "
                        + players[0].playerBoard.getTotalVP(), null, GUIElement.Pure));
                players[0].net.send(new ServerMessageGameOver());

                return;
            }
            players[0].net.send(new ServerMessageView(turn, null, GUIElement.Solo));

            //Check Pope for pc
            if (pc.getDarkFaith() >= pope.value) {
                if (players[0].playerBoard.getFaith() >= pope.min) {
                    players[0].playerBoard.addPope(pope.vp);
                    players[0].net.send(new ServerMessageView(
                            "Lorenzo il Magnifico activated a new Vatican Report. You receive " + pope.vp + " additional Victory Points.\n" +
                                    "Next Report will trigger at "+pope.value+" faith.",
                            null, GUIElement.Pure));
                } else
                    players[0].net.send(new ServerMessageView(
                            "Lorenzo il Magnifico activated a new Vatican Report. Unfortunately you don't receive any additional Victory Points.\n" +
                                    "Next Report will trigger at "+pope.value+" faith.",
                            null, GUIElement.Pure));


                nextPope();
            }
        }
        while(true);
    }

    private void nextPope() {
        switch (pope){
            case FIRST: pope = Pope.SECOND;break;
            case SECOND: pope = Pope.THIRD;break;
            case THIRD: pope = Pope.DONE;break;
            default: throw new IllegalStateException();
        }
    }


    /**
     * starts a multiplayer game
     */
    private void startGame(){
        Log.logger.info("Game actually started");
        boolean endGame = false;
        do {
            int disconnectionCounter = 0;
            for(Player each : this.players) {
                boolean done = false;
                boolean action = false;
                while(!done){
                    try {
                        showAllGame(each);
                        if (!action) action = each.turn(false);
                        else  each.turn(true);
                        Log.logger.info("Setting action to "+action);
                    }
                    catch(EndTurnException e){done = true;}
                    catch(DisconnectedException e){done = true; disconnectionCounter++;}
                }
                checkPope();
                if(!endGame) endGame = checkEndGame();
            }
            if (disconnectionCounter >= players.length)return;
        }
        while (!endGame);
        finalSummary();
    }

    /**
     * shows all virtual table
     * @param currentPlayer player on duty
     * @throws DisconnectedException if impossible to send messages to player
     */
    private void showAllGame(Player currentPlayer) throws DisconnectedException {
        showPlayersBoards(currentPlayer);
        currentPlayer.net.send(new ServerMessageView(devCardBoard.topView(), Serializer.serialize(devCardBoard), GUIElement.DevBoard));
        currentPlayer.net.send(new ServerMessageView(currentPlayer.playerBoard.playerBoardView("YOU"), Serializer.serialize(currentPlayer.playerBoard), GUIElement.PB));
        currentPlayer.net.send(new ServerMessageView(showLeaderCards(currentPlayer)));
        currentPlayer.net.send(new ServerMessageView(market.view(), Serializer.serialize(market), GUIElement.Market));
    }

    /**
     * show player on duty his leader cards
     * @param currentPlayer player on duty
     * @return what to send and print
     */
    private String showLeaderCards(Player currentPlayer) {

        LeaderCard[] tempLeaders = currentPlayer.playerBoard.getLeaderCard();
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

    /**
     * shows all player Board in the game
     * @param currentPlayer player on duty
     * @throws DisconnectedException if the is a disconnection during the game
     */
    private void showPlayersBoards(Player currentPlayer) throws DisconnectedException {
        for (Player player : players) {
            if(player!=currentPlayer) {

                currentPlayer.net.send(new ServerMessageView(player.playerBoard.playerBoardView(player.playerId) , Serializer.serialize(player.playerBoard), GUIElement.OtherPB));

                String[] cards = new String[]{"",""};

                for(int i = 0; i<2; i++) {
                    if(player.playerBoard.getLeaderCard(i)==null){
                        cards[i] =cards[i].concat("══════════════╗\n"+
                                "   \\ /\t\t\n" +
                                "    X\t\t\n" +
                                "   / \\\t\t\n")+
                                "══════════════╝\n";
                    }
                    else {
                        cards[i] = player.playerBoard.getLeaderCard(i).getIsActive() ? player.playerBoard.getLeaderCard(i).view() :
                                ("══════════════╗\n" +
                                        " ████████████  \n" +
                                        " ████████████  \n" +
                                        " ████████████  \n" +
                                        "══════════════╝\n");
                    }
                }

                currentPlayer.net.send(new ServerMessageView("Leader cards:\n"+ViewHelper.displayS2S(cards)));
            }
        }
    }

    /**
     * add players to the game
     * @param players selected player for this game
     */
    public void setPlayers(Player[] players) {
        this.players= players;
    }

    /**
     * checks end game
     * @return true if game is ended
     */
    private boolean checkEndGame(){
        for(Player each : this.players) {
            if(each.playerBoard.getFaith() >= 24 || each.playerBoard.getDevCardsNumber() >= 7) {
                for(Player every : this.players){
                    try{every.net.send(new ServerMessageView("The Game will be over at the end of this round!",null,GUIElement.Pure));}
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
        Log.logger.info("OK1 ");
        createLeaderDeck();
        Log.logger.info("OK2 ");
        createMarket();
        Log.logger.info("OK3 ");
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
    /**
     * creates Market
     * @throws FileNotFoundException if files.json is not found
     */
    private void createMarket() throws FileNotFoundException {
        Marble[] marbles= new Gson().fromJson(new FileReader("src/main/resources/Marbles.json"),Marble[].class);
        this.market = new Market(marbles);
    }

    /**
     * send rankings after the end of the game
     */
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
                each.net.send(new ServerMessageView(string+"\n"+(each.equals(winner) ? "You Won!" : "You Lost!"),null,GUIElement.Pure));
                each.net.send(new ServerMessageGameOver());
            }
            catch (DisconnectedException ignored){}
        }
    }

    /**
     * add faith to other player if not all resources taken from market are deposited
     * @param pb player that took resources from market
     * @param amount number of resources discharged
     */
    public void discardsToFaith(PlayerBoard pb, int amount) {
        for (Player player : this.players) {
            if (player.playerBoard != pb) player.playerBoard.addFaith(amount);
        }
        if(pc != null)pc.addFaith(amount);
    }

    /**
     * faith track rules controller
     */
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
                    each.net.send(new ServerMessageView(trigger.equals(each.playerId) ?
                            "You" : trigger + " activated a new Vatican Report. You receive "+ pope.vp+" additional Victory Points.\n" +
                            "Next Report will trigger at "+pope.value+" faith.",
                            null, GUIElement.Pure));
                } else
                    each.net.send(new ServerMessageView(
                            trigger + " activated a new Vatican Report. Unfortunately you don't receive any additional Victory Points.\n" +
                                    "Next Report will trigger at "+pope.value+" faith.",
                            null, GUIElement.Pure));
            }
            catch (DisconnectedException ignored){}
        }
        nextPope();
    }

}

