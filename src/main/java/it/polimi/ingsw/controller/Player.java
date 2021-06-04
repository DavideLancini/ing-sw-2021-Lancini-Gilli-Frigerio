package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.*;
import it.polimi.ingsw.network.messages.*;

import java.net.ServerSocket;
import java.util.logging.Logger;

public class Player extends Thread{
    private static Logger logger;
    public ConnectionInterface net;
    public PlayerBoard playerBoard;
    public String playerId;
    private Controller controller;
    private LeaderCard[] templeaders;

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

    private void addResource(int numOfResource) throws Exception {
        //TODO: ask resource to add
        int i=0;
        Resource[] resources = new Resource[numOfResource];
        for(Resource resource : resources) {
            playerBoard.getDepot().deposit(resource, i);
            i++;
        }
    }

    public boolean turn(boolean mainAction) throws EndTurnException, DisconnectedException {
        boolean action = false;

        net.send(new ServerMessageTurn(mainAction));
        ClientMessage message = net.receive();

        return action = message.resolve(controller);
    }

    public void receiveLeaders() throws DisconnectedException {
        ClientMessageChosenLeaders message = (ClientMessageChosenLeaders) net.receive();
        setLeaders(message.getPositions());
        logger.info("Leaders received and set");
    }


    public void secondPlayer() throws Exception {
        addResource(1);
    }
    public void thirdPlayer() throws Exception {
        addResource(1);
        playerBoard.addFaith(1);
    }
    public void fourthPlayer() throws Exception {
        addResource(2);
        playerBoard.addFaith(1);
    }

    public void drawLeaderCards(LeaderCard[] leaders) throws DisconnectedException {
        this.templeaders = leaders;
        String[] stringleaders = new String[4];
        for(int i = 0;i< leaders.length; i++) stringleaders[i] = leaders[i].view();

        net.send(new ServerMessageChooseLeaders(stringleaders));
    }

    /**
     * called when receiving ClientMessageChosenLeaders before a game
     * @param positions received in message
     */
    private void setLeaders(int[] positions){
        this.playerBoard.setLeaders(new LeaderCard[]{this.templeaders[positions[0]], this.templeaders[positions[1]]});
    }

    public void setController(Controller controller){this.controller = controller;}

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
                Queue.enterQueue(joinGame.getGameMode(), this);
            }
        } catch (DisconnectedException e) {
            //TODO: manage error
        }
        //This player is in a game

    }

}
