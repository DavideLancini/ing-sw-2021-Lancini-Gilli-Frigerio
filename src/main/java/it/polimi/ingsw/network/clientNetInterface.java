package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;

public class clientNetInterface {
    private boolean isConnected = false;
    private boolean isLogged = false;
    private Sender sender;
    private Listener listener;

    public boolean connect(String serverAddress, int serverPort, int localPort){
        if(!isConnected & !isLogged){
            sender = new Sender(serverAddress, serverPort);
            isConnected = sender.connect();

            Thread t = new Thread(sender);
            t.start();


        }else{
            //TODO warning, sender already connected
        }
        return isConnected;
    }


    /*
    public boolean login(){
        listener
        if(isConnected & !isLogged){
            Message x = null;
            sender.send(x);
            Message y = listener.receive();
            isLogged = y.response;
        }
        return isLogged;

    }
    */


    public void send(Message message){
        sender.send(message);
    }

    public Message receive(){
        return listener.receive();
    }
}
