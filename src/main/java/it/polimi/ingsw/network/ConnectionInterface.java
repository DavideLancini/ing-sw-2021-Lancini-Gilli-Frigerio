package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ClientMessageLocalPort;

import java.net.ServerSocket;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class ConnectionInterface{
    private static Logger logger;
    private Sender sender;
    private Listener listener;

    public ConnectionInterface(ServerSocket fatherSocket, Logger logger) throws DisconnectedException {
        ConnectionInterface.logger = logger;
        //Create a new Listener
        this.listener = new Listener(fatherSocket, logger);
        //Receive the port to witch connect from the first message
        int clientPort;
        Message message = Serializer.deserializeMessage(listener.receive());
        if(message instanceof ClientMessageLocalPort) {
            clientPort = ((ClientMessageLocalPort)message).getPort();
        }else{
            throw new DisconnectedException("Port not usable");
        }
        //Create a Sender
        try {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                //TODO
            }
            this.sender = new Sender(listener.getTargetAddress(), clientPort);
        } catch (DisconnectedException e) {
            throw new DisconnectedException("Failed to connect");
        }
    }

    public void send(Message message) throws DisconnectedException {
        int tries = 5;
        while(tries>0){
            try{
                String rawMessage = Serializer.serialize(message);
                sender.send(rawMessage);
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