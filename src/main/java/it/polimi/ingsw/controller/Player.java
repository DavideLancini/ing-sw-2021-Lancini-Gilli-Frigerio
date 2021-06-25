package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.network.*;
import it.polimi.ingsw.network.messages.*;

import javax.swing.*;
import java.net.ServerSocket;
import java.util.logging.Logger;

/**
 * Class Player
 * @author gruppo 12
 */
public class Player extends Thread{
    private static Logger logger;
    public ConnectionInterface net;
    public PlayerBoard playerBoard;
    public String playerId;
    private Controller controller;
    private LeaderCard[] tempLeaders;

    /**
     * Constructor
     * @param father serverSocket
     * @param logger Logger
     */
    public Player(ServerSocket father, Logger logger){
        Player.logger = logger;
        try {
            this.net = new ConnectionInterface(father, logger);
            logger.info("player created");
        } catch (DisconnectedException e) {
            logger.info("Player cannot create a connection");
        }
        ServerNetInterface.addPlayer();
    }

    /**
     * Add in the deposit the starting resources
     * @param numOfResource 1 for second and third player, 2 for fourth.
     * @throws DisconnectedException if unable to send or receive messages
     */
    private void addResource(int numOfResource) throws DisconnectedException {
        for(int i=0;i<numOfResource;i++) {
            net.send(new ServerMessageAddResource());
            logger.info("waiting Player selected Resource");
            ClientMessagePlaceResource message = (ClientMessagePlaceResource) net.receive();
            logger.info("Player selected Resource");
            try {
                playerBoard.getDepot().deposit(message.getResource(),i);
            } catch (Exception e) {
                logger.info("THIS SHOULD NOT BE POSSIBLE");
            }
        }
    }

    /**
     * manages turn of player
     * @param mainAction true if player has done 1 of the three main action in this turn
     * @return true if actions of player are done
     * @throws EndTurnException if player ended the turn
     * @throws DisconnectedException if unable to send or receive messages
     */
    public boolean turn(boolean mainAction) throws EndTurnException, DisconnectedException {
        net.send(new ServerMessageTurn(mainAction));
        ClientMessage message = net.receive();

        return message.resolve(controller);
    }

    /**
     * receives and set 2 selected leader cards into playerBoard
     * @throws DisconnectedException if unable to send or receive messages
     */
    public void receiveLeaders() throws DisconnectedException {
        ClientMessageChosenLeaders message = (ClientMessageChosenLeaders) net.receive();
        setLeaders(message.getPositions());
        logger.info("Leaders received and set");
    }

    /**
     * additional setup base on number of players
     * @throws DisconnectedException if unable to send or receive messages
     */
    public void secondPlayer() throws DisconnectedException {
        addResource(1);
    }
    /**
     * additional setup base on number of players
     * @throws DisconnectedException if unable to send or receive messages
     */
    public void thirdPlayer() throws DisconnectedException {
        addResource(1);
        playerBoard.addFaith(1);
    }
    /**
     * additional setup base on number of players
     * @throws DisconnectedException if unable to send or receive messages
     */
    public void fourthPlayer() throws DisconnectedException {
        addResource(2);
        playerBoard.addFaith(1);
    }

    /**
     * sends card to be selected by the player at the start of the game
     * @param leaders 4 leader cards
     * @throws DisconnectedException if unable to send or receive messages
     */
    public void drawLeaderCards(LeaderCard[] leaders) throws DisconnectedException {
        this.tempLeaders = leaders;
        String[] stringleaders = new String[4];
        String[] icons = new String[4];
        for(int i = 0;i< leaders.length; i++) {stringleaders[i] = leaders[i].view(); icons[i] = leaders[i].getImage();}

        net.send(new ServerMessageChooseLeaders(stringleaders, icons));
    }

    /**
     * called when receiving ClientMessageChosenLeaders before a game
     * @param positions received in message
     */
    private void setLeaders(int[] positions){
        this.playerBoard.setLeaders(new LeaderCard[]{this.tempLeaders[positions[0]], this.tempLeaders[positions[1]]});
    }

    public void setController(Controller controller){this.controller = controller;}


    /**
     * thread of player/client
     */
    @Override
    public void run() {
        //Get IdPlayer
        try {
            Message temp1=net.receive();
            if(temp1.getType()== MessageType.CreateGame) {
                ClientMessageCreateGame createGame = (ClientMessageCreateGame) temp1;
                this.playerId= createGame.CreateGame();
            }
            else {
                ClientMessageJoinGame joinGame = (ClientMessageJoinGame) temp1;
                this.playerId= joinGame.getPlayerId();
                //Enter queue
                int gameMode = joinGame.getGameMode();
                if (gameMode==1)
                    new Game(this);
                else Queue.enterQueue(gameMode, this);
            }
        } catch (DisconnectedException e) {
            //TODO: manage error
        } catch (Exception e) {
            e.printStackTrace();
        }
        //This player is in a game

    }

}
