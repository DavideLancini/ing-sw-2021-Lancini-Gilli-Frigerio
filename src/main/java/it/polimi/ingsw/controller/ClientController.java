package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.*;
import it.polimi.ingsw.network.components.*;
import it.polimi.ingsw.view.Manager;
import it.polimi.ingsw.view.cli.CLIActionManager;
import it.polimi.ingsw.view.gui.GUIActionManager;

public class ClientController {
    private NetBridge net;
    private Manager manager;

    public void setup (NetBridge net){
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

            switch(((ServerMessage)message).getType()){
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
        net.send(new MessageEndTurn());
    }

    public void takeResources(boolean isRow, int position){
        net.send(new MessageTakeResources(isRow, position));

    }
    public void buyDevCard(Level level, CardColor color, int column){
        net.send(new MessageBuyDevCard(level, color, column));

    }
    public void produce(boolean[] activated){
        net.send(new MessageProduce(activated));

    }
    public void activateLeader(int position){
        net.send(new MessageLeaderActivation(position));

    }
    public void sellLeader(int position){
        net.send(new MessageSellLeader(position));

    }
    public void setResource(Resource resource, int position){
        net.send(new MessageSetResource(resource, position));

    }
    public void tryDepot(Resource[] resource){
        net.send(new MessageTryDepotConfiguration(resource));

    }

}
