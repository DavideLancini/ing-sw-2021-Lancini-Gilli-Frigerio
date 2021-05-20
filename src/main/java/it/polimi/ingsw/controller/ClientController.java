package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.*;

public class ClientController {


    public static void takeResources(boolean isRow, int position){
        //TODO: send
        Message message = new MessageTakeResources(isRow, position);

    }

    public static void buyDevCard(Level level, CardColor color, int column){
        //TODO: send
        Message message = new MessageBuyDevCard(level, color, column);

    }

    public static void produce(boolean[] activated){
        //TODO: send
        Message message = new MessageProduce(activated);

    }

    public static void activateLeader(int position){
        //TODO: send
        Message message = new MessageLeaderActivation(position);

    }

    public static void sellLeader(int position){
        //TODO: send
        Message message = new MessageSellLeader(position);

    }

    public static void setResource(Resource resource, int position){
        //TODO: send
        Message message = new MessageSetResource(resource, position);

    }

    public static void tryDepot(Resource[] resource){
        //TODO: send
        Message message = new MessageTryDepotConfiguration(resource);

    }

}
