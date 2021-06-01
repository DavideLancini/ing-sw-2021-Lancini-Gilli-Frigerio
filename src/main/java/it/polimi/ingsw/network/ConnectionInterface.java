package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;

import java.net.ServerSocket;

public class ConnectionInterface{
    private Sender sender;
    private Listener listener;

    public ConnectionInterface(String clientAddress, int clientPort,  ServerSocket fatherSocket) throws DisconnectedException {
        try {
            this.sender = new Sender(clientAddress, clientPort);
        } catch (DisconnectedException e) {
            throw new DisconnectedException("Failed to connect");
        }
        this.listener = new Listener(fatherSocket);
    }

    public void send(ClientMessage message) throws DisconnectedException {
        int tries = 5;
        while(tries>0){
            try{
                sender.send(message);
                return;
            }catch (DisconnectedException e){
                tries--;
            }
        }
        throw new DisconnectedException("Failed to send");
    }

    public ClientMessage receive() throws DisconnectedException{
        return listener.receive();
    }
}