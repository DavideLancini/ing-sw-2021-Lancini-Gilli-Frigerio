package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.view.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Socket class that send messages
 *
 * @author Group 12
 */
public class Sender{
    private String logName;
    private Socket socket;
    private DataOutputStream outStream;

    /**
     * Constructor for the Sender. The socket also tries to connect right away.
     *
     * @throws DisconnectedException when something fails (at this stage why it fails is not critical and is logged as warning)
     */
    public Sender(String destinationAddress, int destinationPort) throws DisconnectedException {
        this.logName = "SenderTo(" + destinationAddress + ":" + destinationPort + ")" ;
        //Connect Socket
        try {
            this.socket = new Socket(destinationAddress, destinationPort);
            Log.logger.info(logName + " is connected");
        } catch (IOException errorMessage) {
            Log.logger.warning(logName + " has failed connection");
            throw new DisconnectedException("Connection Failed");
        }
        //Activate Output stream
        try {
            this.outStream = new DataOutputStream(socket.getOutputStream());
            Log.logger.info(logName + " has created an output stream");
        } catch (IOException e) {
            Log.logger.warning(logName + " has failed to activate an output stream");
            throw new DisconnectedException("Stream Creation Failed");
        }
    }

    /**
     * The Send Method is the Main method used for the Sender.
     * It tries to send one string from its outStream.
     *
     * @throws DisconnectedException when the outStream is unavailable
     */
    public void send(String rawMessage) throws DisconnectedException {
        try {
            outStream.writeUTF(rawMessage);
            Log.logger.info(logName + " has sent a message: "+ rawMessage);
        } catch (IOException errorMessage) {
            Log.logger.warning(logName + " has failed to send a message");
            throw new DisconnectedException("Sending Failed");
        }
    }

    /**
     * Close this Socket, the failure is not critical so it only logs a warning
     */
    public void close() {
        try {
            this.socket.close();
            Log.logger.info(this.logName + ": has been closed");
        } catch (IOException e) {
            Log.logger.warning(this.logName + ": has failed the closure procedure");
        }
    }
}