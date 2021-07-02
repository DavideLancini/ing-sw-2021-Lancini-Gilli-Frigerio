package it.polimi.ingsw.psp12.network;

import it.polimi.ingsw.psp12.network.components.Listener;
import it.polimi.ingsw.psp12.network.components.Sender;
import it.polimi.ingsw.psp12.network.components.Serializer;
import it.polimi.ingsw.psp12.view.Log;

import java.util.LinkedList;

public class NetInterface {
    private static final LinkedList<Message> ClientQueue = new LinkedList<>();
    private static final LinkedList<Message> ServerQueue = new LinkedList<>();
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


    /**
     * The send method take a message, serialize it with the Serializer and send the string via the sender.
     * It tries to send the message 5 times, afterwards the connection is declared Interrupted
     * Used by subclasses
     * @throws DisconnectedException after 5 failed tries and every component is closed
     */
    static void send(Message message, Sender sender, Listener listener) throws DisconnectedException {
        int tries = 5;
        while(tries>0){
            try{
                String rawMessage = Serializer.serialize(message);
                sender.send(rawMessage);
                return;
            }catch (DisconnectedException e){
                tries--;
            }
        }
        sender.close();
        listener.close();
        ServerNetworkController.removePlayer();
        throw new DisconnectedException("Failed to send");
    }
}
