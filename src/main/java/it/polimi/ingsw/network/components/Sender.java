package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.DisconnectedException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Socket class that send messages
 * The logger is shared with all the sender created
 *
 * @author Lancini Davide
 */
public class Sender{
    private static Logger senderLogger = Logger.getLogger("SenderLogger");

    private String logName;
    private Socket socket;
    private DataOutputStream outStream;

    public Sender(String destinationAddress, int destinationPort) throws DisconnectedException {

        senderLogger.setLevel(Level.ALL);
        this.logName = "SenderTo(" + destinationAddress + ":" + destinationPort + ")" ;
        //Connect Socket
        try {
            this.socket = new Socket(destinationAddress, destinationPort);
            senderLogger.log(Level.INFO,logName + " is connected");
        } catch (IOException errorMessage) {
            senderLogger.log(Level.WARNING,logName + " has failed connection", new DisconnectedException("Connection Failed"));
        }
        //Activate Output stream
        try {
            this.outStream = new DataOutputStream(socket.getOutputStream());
            senderLogger.log(Level.INFO,logName + " has created an output stream");
        } catch (IOException e) {
            senderLogger.log(Level.WARNING,logName + " has failed to activate an output stream");
            throw new DisconnectedException("Stream Creation Failed");
        }
    }

    public void send(String rawMessage) throws DisconnectedException {
        try {
            outStream.writeUTF(rawMessage);
            senderLogger.log(Level.INFO,logName + " has sent a message: "+ rawMessage);
        } catch (IOException errorMessage) {
            senderLogger.log(Level.WARNING,logName + " has failed to send a message");
            throw new DisconnectedException("Sending Failed");
        }
    }

    /**
     * Close this Socket
     *
     * @author Lancini Davide
     */
    public void close() {
        try {
            this.socket.close();
            senderLogger.info(this.logName + ": has been closed");
        } catch (IOException e) {
            senderLogger.warning(this.logName + ": has failed the closure procedure");
        }
    }
}