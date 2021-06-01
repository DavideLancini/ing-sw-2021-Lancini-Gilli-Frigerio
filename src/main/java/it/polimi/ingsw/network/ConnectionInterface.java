package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.MessageLocalPort;

import java.net.ServerSocket;

public class ConnectionInterface{
    private Sender sender;
    private Listener listener;

    public ConnectionInterface(ServerSocket fatherSocket) throws DisconnectedException {
        //Create a new Listener
        this.listener = new Listener(fatherSocket);
        //Receive the port to witch connect from the first message
        int clientPort;
        ClientMessage message = Serializer.deserializeMessage(listener.receive());
        if(message instanceof MessageLocalPort) {
            clientPort = ((MessageLocalPort)message).getPort();
        }else{
            throw new DisconnectedException("Port not usable");
        }
        //Create a Sender
        try {
            this.sender = new Sender(listener.getTargetAddress(), clientPort);
        } catch (DisconnectedException e) {
            throw new DisconnectedException("Failed to connect");
        }
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
        return Serializer.deserializeMessage(listener.receive());
    }
}