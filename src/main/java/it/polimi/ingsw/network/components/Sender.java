package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.ClientMessage;
import it.polimi.ingsw.network.DisconnectedException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Socket class that send messages
 * The logger is shared with all the sender created(in Server)
 *
 * @author Lancini Davide
 */
public class Sender{
    public static final Logger logger = Logger.getLogger("SenderLogger");

    private String logName;
    private Socket socket;
    private DataOutputStream outStream = null;

    public Sender(String destinationAddress, int destinationPort) throws DisconnectedException {
        //Connect Socket
        logger.setLevel(Level.ALL);
        this.logName = "Sender (" + destinationAddress + ":" + destinationPort + ")" ;
        try {
            this.socket = new Socket(destinationAddress, destinationPort);
        } catch (IOException errorMessage) {
            logger.log(Level.WARNING,logName + " has failed connection");
            throw new DisconnectedException("Connection Failed");
        }
        logger.log(Level.INFO,logName + " is connected");
        //Activate Output stream
        try {
            this.outStream = new DataOutputStream(socket.getOutputStream());
            logger.log(Level.INFO,logName + " has created an output stream");
        } catch (IOException e) {
            logger.log(Level.WARNING,logName + " has failed to activate an output stream");
            throw new DisconnectedException("Stream Creation Failed");
        }
    }

    public void send(ClientMessage message) throws DisconnectedException {
        String x = Serializer.serialize(message);
        try {
            outStream.writeUTF(x);
        } catch (IOException errorMessage) {
            logger.log(Level.WARNING,logName + " has failed to send a message");
            throw new DisconnectedException("Sending Failed");
        }
    }

    public boolean close(){
        try {
            this.socket.close();
            logger.log(Level.INFO,logName + " has been closed");
            return false;
        } catch (IOException ioException) {
            //TODO: Manage this error
            return true;
        }
    }
}
