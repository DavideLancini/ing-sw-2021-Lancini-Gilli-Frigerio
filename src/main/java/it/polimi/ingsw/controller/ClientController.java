package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.*;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.view.Manager;
import it.polimi.ingsw.view.cli.CLIActionManager;
import it.polimi.ingsw.view.gui.GUIActionManager;

public class ClientController {
    private ConnectionInterface net;
    private Manager manager;

    public void setup (ConnectionInterface net){
        this.net = net;
    }

    public ClientController(boolean cli) {
        this.manager = cli? new CLIActionManager(this) : new GUIActionManager(this);
    }

    //implements thread?
    public void main(){


        while (true){
            Message message = net.receive();
            //TODO: check if there is actually a message, otherwise wait

            switch(message.getType()){
                case Turn:
                    manager.Turn(((ServerMessageTurn)message).getAction());
                    break;

                case MarketReturn:
                    manager.ArrangeDepot(((ServerMessageMarketReturn)message).getResources());
                    break;

                case View:
                    manager.View(((ServerMessageView)message).view);
                    break;

                case Error:
                    manager.DisplayError(((ServerMessageError)message).getError());
                    break;



            }


        }

    }





    public void endTurn(){
        net.send(new ClientMessageEndTurn());
    }

    public void takeResources(boolean isRow, int position){
        net.send(new ClientMessageTakeResources(isRow, position));

    }
    public void buyDevCard(Level level, CardColor color, int column){
        net.send(new ClientMessageBuyDevCard(level, color, column));

    }
    public void produce(boolean[] activated){
        net.send(new ClientMessageProduce(activated));

    }
    public void activateLeader(int position){
        net.send(new ClientMessageLeaderActivation(position));

    }
    public void sellLeader(int position){
        net.send(new ClientMessageSellLeader(position));

    }
    public void setResource(Resource resource, int position){
        net.send(new ClientMessageSetResource(resource, position));

    }
    public void tryDepot(Resource[] resource){
        net.send(new ClientMessageTryDepotConfiguration(resource));

    }

}
