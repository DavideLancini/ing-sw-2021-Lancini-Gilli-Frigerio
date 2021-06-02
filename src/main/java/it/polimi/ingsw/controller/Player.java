package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ConnectionInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.EndTurnException;

import java.net.ServerSocket;
import java.util.logging.Logger;

public class Player {
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
}
