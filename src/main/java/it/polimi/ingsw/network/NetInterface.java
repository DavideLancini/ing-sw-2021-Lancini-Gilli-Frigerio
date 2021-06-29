package it.polimi.ingsw.network;

import it.polimi.ingsw.view.Log;

import java.util.logging.Logger;

public class NetInterface {
    private static Message MessageQueue;

    public NetInterface(){
        Log.logger.info("Net Interface initialized");
    }

    public void send(Message message) throws DisconnectedException{
        MessageQueue = message;
        Log.logger.info("Message written: " + MessageQueue);
    }

    public Message receive() throws DisconnectedException{
        Log.logger.info("Message read: " + MessageQueue);
        return MessageQueue;
    }
}
