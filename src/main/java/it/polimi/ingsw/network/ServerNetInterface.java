package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.Sender;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageLocalPort;
import it.polimi.ingsw.view.Log;

import java.net.ServerSocket;

import static java.lang.Thread.sleep;

/**
 * Class Server Network Interface.
 * Used by the Server App and identifies a single connection between Server and Client
 *
 * @author Group 12
 */
public class ServerNetInterface extends NetInterface{
    private Sender sender;
    private Listener listener;

    /**
     * Constructor for the Server Network Interface. It receive parameters for the connection and the logger.
     * It first creates a listener and waits for a connection. It will then receive the port on witch to connect the
     * sender
     *
     * @throws DisconnectedException if something fails, and whatever is created to that point is closed
     */
    public ServerNetInterface(ServerSocket fatherSocket) throws DisconnectedException {
        //Create a new Listener
        this.listener = new Listener(fatherSocket);
        Log.logger.info("Listener created");
        //Receive the port to witch connect from the first message
        int clientPort;
        Message message = Serializer.deserializeMessage(listener.receive());
        Log.logger.info("ConnectionInterface is collecting the target port for the sender");
        if(message instanceof ClientMessageLocalPort) {
            clientPort = ((ClientMessageLocalPort)message).getPort();
        }else{
            this.listener.close();
            throw new DisconnectedException("Port not usable");
        }
        Log.logger.info("ConnectionInterface received target port: " + clientPort);
        //Create a Sender
        try {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                //Nothing will interrupt this sleep
            }
            this.sender = new Sender(listener.getTargetAddress(), clientPort);
        } catch (DisconnectedException e) {
            this.sender.close();
            this.listener.close();
            throw new DisconnectedException("Failed to connect");
        }
        ServerNetworkController.addPlayer();
    }

    /**
     * The send method take a message, serialize it with the Serializer and send the string via the sender.
     * It tries to send the message 5 times, afterwards the connection is declared Interrupted
     *
     * @throws DisconnectedException after 5 failed tries and every component is closed
     */
    public void send(Message message) throws DisconnectedException {
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
        this.sender.close();
        this.listener.close();
        ServerNetworkController.removePlayer();
        throw new DisconnectedException("Failed to send");
    }

    /**
     * The receive method, use the Listener.receive method to receive a string from the input stream, then its converted
     * to Message with the deserializer
     *
     * @throws DisconnectedException when it receive an error from the listener, it also closes every component
     */
    public ClientMessage receive() throws DisconnectedException{
        try {
            return Serializer.deserializeMessage(listener.receive());
        }catch(DisconnectedException e){
            this.sender.close();
            this.listener.close();
            ServerNetworkController.removePlayer();
            throw e;
        }
    }
}