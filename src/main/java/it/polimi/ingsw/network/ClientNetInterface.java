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

    public void connect(){
        if(!isConnected & !isLogged){
            //create sender
            this.sender = new Sender(this.serverAddress, this.serverPort);
            Client.logger.log(Level.INFO,"class>method> Sender created on:" + this.serverAddress + this.serverPort);
            //activate sender
            sender.run();
        }else{
            //TODO throw warning (sender already connected)
        }
    }

    public boolean login(){
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
            //start listener
            this.listener.run();
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

    public void send(ClientMessage message){
        sender.send(message);
    }

    public ClientMessage receive(){
        return listener.receive();
    }
}
