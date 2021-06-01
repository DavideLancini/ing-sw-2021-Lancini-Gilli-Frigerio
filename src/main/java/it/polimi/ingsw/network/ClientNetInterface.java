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

    public boolean connect() throws DisconnectedException{
        if(!isConnected & !isLogged){
            //create sender
            try {
                this.sender = new Sender(this.serverAddress, this.serverPort);
            } catch (DisconnectedException e) {
                return false;
            }
            Client.logger.log(Level.INFO,"class>method> Sender created on:" + this.serverAddress + " and port: "+ this.serverPort);
            try {
                father = new ServerSocket(localPort);
            } catch (IOException e) {
                return false;
            }
            listener = new Listener(father);
        }else{
            return false;
        }
        return true;
    }

    public boolean login() throws DisconnectedException{
        if(isConnected & !isLogged){
            // open father socket
            try {
                father = new ServerSocket(localPort);
            } catch (IOException e) {
                //TODO: throw warning (listener not opened)
                return false;
            }
            //create listener
            this.listener = new Listener(father);
            //TODO: create an actual login message
                ClientMessage x = null;
            //send login request
            this.sender.send(x);
            //wait for response
            ClientMessage y = listener.receive();
            //TODO: from Response extract token
        }
        return isLogged;
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
