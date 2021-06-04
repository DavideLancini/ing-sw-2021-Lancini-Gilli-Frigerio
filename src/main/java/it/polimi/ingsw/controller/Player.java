package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.*;
import it.polimi.ingsw.network.messages.*;

import java.net.ServerSocket;
import java.util.logging.Logger;

public class Player extends Thread{
    private static Logger logger;
    private ConnectionInterface net;
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

    public boolean turn(boolean mainAction) throws EndTurnException {
        boolean action = false;
        //TODO:
        //net.send(new ServerMessageTurn(mainAction));
        //wait for response
        //action = message.resolve(controller)

        //temp
        throw new EndTurnException();
        //return /*action*/ true;
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
        net.send(new ServerMessageChooseLeaders(leaders));
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
        try {
            Message temp1=net.receive();
            if(temp1.getType()== MessageType.CreateGame) {
                ClientMessageCreateGame createGame = (ClientMessageCreateGame) temp1;
                this.playerId= createGame.CreateGame();
            }
            else {
                ClientMessageJoinGame joinGame = (ClientMessageJoinGame) temp1;
                this.playerId= joinGame.joinGame();
            }
        } catch (DisconnectedException e) {
            e.printStackTrace();
        }
        //TODO: Get a PlayerID this.playerId = "xXx_Angelino_Alfano_xXx"; //TEMP

        //TODO: Wait for a Join/Create Game

        //TODO: Create controller

        //Read Messages
        while(ServerNetInterface.isON){
            logger.info("(Player)"+this.playerId+": is waiting for a message from the client");
            try {
                Message temp = net.receive();

                //TODO: temp.resolve(* manca il controller *)
            } catch (DisconnectedException e) {
                ServerNetInterface.removePlayer();
                interrupt();
            }
        }
    }

}
