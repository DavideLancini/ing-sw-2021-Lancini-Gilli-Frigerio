package it.polimi.ingsw.network;

import it.polimi.ingsw.Client;
import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;

public class clientNetInterface {
    private final String serverAddress;
    private final int serverPort;
    private final int localPort;

    private boolean isConnected = false;
    private final boolean isLogged = false;
    private String token;

    private Sender sender;
    private Listener listener;
    private ServerSocket father;

    public clientNetInterface(String serverAddress, int serverPort, int localPort){
        this.serverAddress=serverAddress;
        this.serverPort=serverPort;
        this.localPort=localPort;
    }

    public boolean connect(){
        if(!isConnected & !isLogged){
            //create sender
            this.sender = new Sender(this.serverAddress, this.serverPort);
            Client.logger.log(Level.INFO,"class>method> Sender created on:" + this.serverAddress + this.serverPort);
            //connect sender
            if(this.sender.connect()){
                //activate output stream
                this.isConnected = this.sender.activateStream();
                //TODO: throw info
            }else{
                //TODO: throw warning (not connected)
                return false;
            }
        }else{
            //TODO throw warning (sender already connected)
        }
        return isConnected;
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
            listener = new Listener(father);
            //start listener
            listener.start();
            //TODO: create an actual login message
                Message x = null;
            //send login request
            sender.send(x);
            //wait for response
            Message y = listener.receive();
            //TODO: from Response extract token
        }
        return isLogged;
    }

    public void send(Message message){
        sender.send(message);
    }

    public Message receive(){
        return listener.receive();
    }
}
