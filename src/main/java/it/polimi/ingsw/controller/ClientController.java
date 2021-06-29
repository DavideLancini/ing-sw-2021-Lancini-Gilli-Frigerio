package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.Message;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.view.Manager;

/**
 * Class ClientController
 * @author Group 12
 */
public class ClientController {
    private ClientNetInterface net;
    private final Manager manager;

    /**
     * setup net information
     * @param net contains all network information
     */
    public void setup (ClientNetInterface net){
        this.net = net;
    }

    /**
     * Constructor for ClientManager
     * @param manager Manager of specific Player/Client
     */
    public ClientController(Manager manager) {
        this.manager = manager;
    }

    /**
     * send ClientMessages based on ServerMassages received
     * @throws DisconnectedException if the is a disconnection during the game
     */
    public void main() throws DisconnectedException {
        while (true){
            Message message = net.receive();

            switch(message.getType()){
                case Turn:
                    net.send(manager.turn(((ServerMessageTurn)message).getAction()));
                    break;

                case MarketReturn:
                    net.send(manager.arrangeDepot(((ServerMessageMarketReturn)message).getResources()));
                    break;

                case View:
                    manager.view((ServerMessageView)message);
                    break;

                case Error:
                    manager.displayError(((ServerMessageError)message).getError());
                    break;

                case ChooseLeaders:
                    net.send(manager.chooseLeaders((ServerMessageChooseLeaders)message));
                    break;

                case AddResource:
                    net.send(manager.addResource());
                    manager.waitForTurn();
                    break;

                case TwoMarbleLeaders:
                    net.send(manager.chooseResource(((ServerMessageTwoMarbleLeaders)message).getResources()));
                    break;

                case GameOver:
                    return;
            }
        }
    }


}
