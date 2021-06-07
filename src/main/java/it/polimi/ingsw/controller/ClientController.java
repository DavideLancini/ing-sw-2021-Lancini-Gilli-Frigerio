package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.view.CLIActionManager;
import it.polimi.ingsw.view.Manager;
import it.polimi.ingsw.view.gui.GUIActionManager;

public class ClientController {
    private ClientNetInterface net;
    private Manager manager;

    public void setup (ClientNetInterface net){
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
                    net.send(manager.turn(((ServerMessageTurn)message).getAction()));
                    break;

                case MarketReturn:
                    net.send(manager.arrangeDepot(((ServerMessageMarketReturn)message).getResources()));
                    break;

                case View:
                    manager.view(((ServerMessageView)message).view);
                    break;

                case Error:
                    manager.displayError(((ServerMessageError)message).getError());
                    break;

                case ChooseLeaders:
                    net.send(manager.chooseLeaders(((ServerMessageChooseLeaders)message).getLeaders()));
                    break;
                case AddResource:
                    net.send(manager.addResource());
                    break;
            }
        }
    }


}
