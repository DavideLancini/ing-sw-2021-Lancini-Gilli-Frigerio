package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;

import java.net.ServerSocket;

public class ConnectionInterface{
    private Sender sender;
    private Listener listener;

    public ConnectionInterface(String clientAddress, int clientPort,  ServerSocket fatherSocket){
        this.sender = new Sender(clientAddress, clientPort);
        sender.run();
        this.listener = new Listener(fatherSocket);
    }

    public boolean send(ClientMessage message){
        sender.send(message);
        //TODO: return actual confirmation
        return true;
    }

    public ClientMessage receive(){
        return listener.receive();
    }
}