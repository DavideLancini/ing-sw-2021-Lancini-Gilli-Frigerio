package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.*;
import it.polimi.ingsw.network.messages.EndTurnException;

import java.net.ServerSocket;
import java.util.logging.Logger;

public class Player extends Thread{
    private static Logger logger;
    private ConnectionInterface net;
    public PlayerBoard playerBoard;
    public String playerId;
    private Controller controller;

    public Player(ServerSocket father, Logger logger){
        Player.logger = logger;
        try {
            net = new ConnectionInterface(father, logger);
        } catch (DisconnectedException e) {
            //TODO: manage error
        }
        ServerNetInterface.addPlayer();
    }

    public void addResource(int numOfResource) throws Exception {
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

    @Override
    public void run() {
        //TODO: Get a PlayerID
        this.playerId = "xXx_Angelino_Alfano_xXx"; //TEMP

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
