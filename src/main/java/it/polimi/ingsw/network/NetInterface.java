package it.polimi.ingsw.network;

import java.util.logging.Logger;

public class NetInterface {
    private static Message MessageQueue;
    private static Logger logger;

    public NetInterface() {
    }

    public NetInterface(Logger logger){
        NetInterface.logger = logger;
        logger.info("Net Interface initialized with a logger");
    }

    public void send(Message message) throws DisconnectedException{
        MessageQueue = message;
        logger.info("Message written: " + MessageQueue);
    }

    public Message receive() throws DisconnectedException{
        logger.info("Message read: " + MessageQueue);
        return MessageQueue;
    }
}
