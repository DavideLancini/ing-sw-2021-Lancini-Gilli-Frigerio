package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.DisconnectedException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * A Listener is a Socket that receive Strings from the Sender.
 * It needs a fatherSocket (ServerSocket) to be initialized.
 * All Listeners are opened on the same Port but create different InputStream
 * All Listener shares the same logger (received from the initialization of the first Listener)
 *
 * @author Lancini Davide
 */
public class Listener {
    private static Logger logger;

    private Socket listenerSocket;
    private DataInputStream inputStream;
    private String listenerName;

    /**
     * Constructor for the Listener the listener will be stuck on accept until a connection is made.
     *
     * @throws DisconnectedException when one of the function fail
     * @author Lancini Davide
     */
    public Listener(ServerSocket fatherSocket, Logger logger) throws DisconnectedException {
        Listener.logger = logger;
        //Accept connection
        try {
            this.listenerSocket = fatherSocket.accept();
            this.listenerName = "Listener" + getTargetAddress();
            logger.info(this.listenerName + " has accepted a connection");
        } catch (IOException error) {
            logger.warning("Cannot accept a connection");
            throw new DisconnectedException("Listener cannot accept connection");
        }
        //Open input stream
        try {
            this.inputStream = new DataInputStream(new BufferedInputStream(this.listenerSocket.getInputStream()));
            logger.info(this.listenerName + "has created a stream ");
        } catch (IOException error) {
            logger.warning(this.listenerName + "Listener failed to open a stream");
            throw new DisconnectedException("Failed to open a stream");
        }
    }

    /**
     * The Receive Method is the Main method used for the Listener.
     * It tries to read one string from its inputStream.
     *
     * @return an empty string if there are no messages
     * @throws DisconnectedException when the Stream is unavailable
     * @author Lancini Davide
     */
    public String receive() throws DisconnectedException {
        String rawMessage = "";
        while(rawMessage.equals("")){
            try {
                rawMessage = this.inputStream.readUTF();
                logger.info(this.listenerName + "has received: " + rawMessage);
            } catch (IOException e) {
                throw new DisconnectedException("Failed to receive");
            }
        }
        return rawMessage;
    }

    /**
     * getTarget Address collects the address from the socket (as an INetAddress), it casts it into a string and removes
     * the "/" and the port number (after a ":")
     * Ex.: /192.168.1.123:1001 -> 192.168.1.123
     * It's only used by the Server Network to open a Sender to the right Client
     *
     * @author Lancini Davide
     */
    public String getTargetAddress() {
        String address = this.listenerSocket.getRemoteSocketAddress().toString();
        address = address.substring(1, address.indexOf(":"));
        logger.info(this.listenerName + ": has been requested its target address:" + address);
        return address;
    }
}