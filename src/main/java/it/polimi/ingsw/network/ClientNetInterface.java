package it.polimi.ingsw.network;

import it.polimi.ingsw.Client;
import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientNetInterface {
    private Logger logger = Logger.getLogger("CNILogger");

    private String serverAddress;
    private int serverPort;
    private int localPort;

    private boolean isConnected = false;
    private final boolean isLogged = false;
    private String token;

    private Sender sender;
    private Listener listener;
    private ServerSocket father;

    public void setParameters(String serverAddress, int serverPort, int localPort){
        this.serverAddress=serverAddress;
        this.serverPort=serverPort;
        this.localPort=localPort;
    }

    public void connect() throws DisconnectedException{
        if(!isConnected & !isLogged){
            //create sender
            try {
                this.sender = new Sender(this.serverAddress, this.serverPort);
            } catch (DisconnectedException e) {
                throw new DisconnectedException("failed to create a sender");
            }
            Client.logger.log(Level.INFO,"class>method> Sender created on:" + this.serverAddress + " and port: "+ this.serverPort);
            try {
                father = new ServerSocket(localPort);
            } catch (IOException e) {
                throw new DisconnectedException("failed to connect");
            }
            listener = new Listener(father);
        }
    }

    public boolean send(ClientMessage message) throws DisconnectedException {
        int tries = 5;
        while(tries>0){
            try{
                sender.send(message);
                return true;
            }catch (DisconnectedException e){
                tries--;
            }
        }
        return false;
    }

    public ClientMessage receive() throws DisconnectedException{
        return listener.receive();
    }
}
