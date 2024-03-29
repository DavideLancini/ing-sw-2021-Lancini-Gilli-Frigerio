package it.polimi.ingsw.psp12.network;

import it.polimi.ingsw.psp12.network.components.Listener;
import it.polimi.ingsw.psp12.network.components.Sender;
import it.polimi.ingsw.psp12.network.components.Serializer;
import it.polimi.ingsw.psp12.network.messages.ClientMessageLocalPort;
import it.polimi.ingsw.psp12.network.messages.ServerMessage;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Class Client Network Interface.
 * Used by the Client App to connect to the server
 * @author Group 12
 */
public class ClientNetInterface extends NetInterface{


    private final Sender sender;
    private Listener listener;
    private final ServerSocket father;

    /**
     * Constructor for the Client Network Interface. It receive parameters for the connection and the logger.
     * It first creates a Sender and a ServerSocket that will host the Listener.
     * The listener is created on a thread as the first Server/Listener operation needs to be parallel, afterwards are
     * both sequential.
     *
     * @throws DisconnectedException if something fails, and whatever is created to that point is closed
     */
    public ClientNetInterface(String serverAddress, int serverPort, int localPort) throws DisconnectedException {

        //create sender
        try {
            this.sender = new Sender(serverAddress, serverPort);
        } catch (DisconnectedException e) {
            throw new DisconnectedException("failed to create a sender");
        }
        try {
            father = new ServerSocket(localPort);
        } catch (IOException e) {
            this.sender.close();
            throw new DisconnectedException("failed to create fatherSocket");
        }
        //Open a listener on a runnable
        Thread temp = new Thread(() -> {
            try {
                listener = new Listener(father);
            } catch (DisconnectedException ignored) {
                //probably this error is impossible
            }
        });
        //Create a message to declare to the server what port should be used to receive messages
        ClientMessageLocalPort message;
        message = new ClientMessageLocalPort(father.getLocalPort());
        String rawMessage = Serializer.serialize(message);
        sender.send(rawMessage);
        temp.start();

    }

    /**
     * The send method take a message, serialize it with the Serializer and send the string via the sender.
     * It tries to send the message 5 times, afterwards the connection is declared Interrupted
     *
     * @throws DisconnectedException after 5 failed tries and every component is closed
     */
    public void send(Message message) throws DisconnectedException {
        send(message, sender, this.listener);
    }

    /**
     * The receive method, use the Listener.receive method to receive a string from the input stream, then its converted
     * to Message with the deserializer
     *
     * @throws DisconnectedException when it receive an error from the listener, it also closes every component
     */
    public ServerMessage receive() throws DisconnectedException{
        try {
            return Serializer.deserializeServerMessage(listener.receive());
        }catch(DisconnectedException e){
            this.sender.close();
            this.listener.close();
            throw e;
        }
    }
}
