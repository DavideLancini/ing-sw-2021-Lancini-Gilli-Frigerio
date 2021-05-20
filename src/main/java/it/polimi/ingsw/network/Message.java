package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.*;

public abstract class Message {

    protected String[] token = new String[12]; //used to identify player and match
    protected int index; //used to keep message in order (the socket will discard messages too old)
    protected MessageType type; //used to identify the type of message

    public void send(){}
    public void resolve(Controller controller){}
}

class MessagePing extends Message{
    private String[] Serialized = new String[32];

    public void send(){
        this.type = MessageType.PING;
    }
}

