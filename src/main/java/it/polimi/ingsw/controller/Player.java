package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.Resource;

public class Player {
    public PlayerBoard playerBoard;
    public String playerId;

    public void addResource(int numOfResource) throws Exception {
        //ask resource to add
        int i=0;
        Resource[] resources = new Resource[numOfResource];
        for(Resource resource : resources)
        playerBoard.getDepot().deposit(resource,i);
        i++;
    }

    public boolean turn() {

        return false;
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
