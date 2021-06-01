package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ConnectionInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.view.CLIActionManager;
import it.polimi.ingsw.view.Manager;
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
    public void main() throws DisconnectedException {
        while (true){
            Message message = net.receive();
            //TODO: check if there is actually a message, otherwise wait

            switch(message.getType()){
                case Turn:
                    net.send(manager.Turn(((ServerMessageTurn)message).getAction()));
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


    public void tryDepot(Resource[] resource) throws DisconnectedException{
        net.send(new ClientMessageTryDepotConfiguration(resource));

    }

}
