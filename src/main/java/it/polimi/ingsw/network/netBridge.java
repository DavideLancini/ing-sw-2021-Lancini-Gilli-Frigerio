package it.polimi.ingsw.network;

import it.polimi.ingsw.network.Message;

public class netBridge {
    Message message;
    public boolean connect(String serverAddress, int serverPort, int localPort){
        return true;
    }

    public boolean login(String username){
        return true;
    }

    public boolean send(Message message){
        this.message = message;
        return true;
    }

    public Message receive(){
        return this.message;
    }
}