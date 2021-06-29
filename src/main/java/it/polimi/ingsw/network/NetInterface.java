package it.polimi.ingsw.network;

import it.polimi.ingsw.view.Log;

import java.util.LinkedList;

public class NetInterface {
    private static LinkedList<Message> ClientQueue = new LinkedList<>();
    private static LinkedList<Message> ServerQueue = new LinkedList<>();
    private static boolean isFirst = true;
    private boolean isClient = true;

    public NetInterface(){
        if (isFirst){
            isFirst = false;
            Log.logger.info("Net Interface initialized for the Client");
        }else{
            this.isClient = false;
            Log.logger.info("Net Interface initialized for the Offline Server");
        }
    }

    public void send(Message message) throws DisconnectedException{
        if(isClient){
            ClientQueue.addLast(message);
            Log.logger.info("Client has written: " + message);
        }else{
            ServerQueue.addLast(message);
            Log.logger.info("Offline Server has written: " + message);
        }
    }

    public Message receive() throws DisconnectedException{
        Message message = null;
        while(message == null){
            if(isClient){
                message = ServerQueue.pollFirst();

            }else{
                message = ClientQueue.pollFirst();
            }
        }
        Log.logger.info("Read: " + message);
        return message;
    }
}