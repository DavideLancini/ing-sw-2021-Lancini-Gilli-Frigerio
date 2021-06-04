package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ClientMessageLocalPort;
import it.polimi.ingsw.network.messages.ServerMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class ClientNetInterface {
    private String serverAddress;
    private int serverPort;
    private int localPort;
    private static Logger logger;

    private boolean isConnected = false;
    private boolean isLogged = false;

    private Sender sender;
    private Listener listener;
    private ServerSocket father;

    public ClientNetInterface(String serverAddress, int serverPort, Logger logger) throws DisconnectedException {

        if (!isConnected & !isLogged) {
            //create sender
            try {
                this.sender = new Sender(serverAddress, serverPort);
            } catch (DisconnectedException e) {
                throw new DisconnectedException("failed to create a sender");
            }
            try {
                father = new ServerSocket(0);
            } catch (IOException e) {
                throw new DisconnectedException("failed to create fatherSocket");
            }
            //Open a listener on a runnable
            Thread temp = new Thread() {
                @Override
                public void run() {
                    try {
                        listener = new Listener(father, logger);
                    } catch (DisconnectedException e) {
                        //TODO: probably this error is impossible
                    }
                }
            };
            //Create a message to declare to the server what port should be used to receive messages
            ClientMessageLocalPort message;
            message = new ClientMessageLocalPort(father.getLocalPort());
            String rawMessage = Serializer.serialize(message);
            sender.send(rawMessage);
            temp.start();
        } else {
            //TODO: warning
        }
    }

    public boolean send(Message message) throws DisconnectedException {
        String rawMessage = Serializer.serialize(message);


        int tries = 5;
        while(tries>0){
            try{
                sender.send(rawMessage);
                return true;
            }catch (DisconnectedException e){
                tries--;
            }
        }
        return false;
    }

    public ServerMessage receive() throws DisconnectedException{
        return Serializer.deserializeServerMessage(listener.receive());
    }
}
