package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.ClientMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender implements Runnable{
    public static final Logger logger = Logger.getLogger("SenderLogger");
    private String destinationAddress;
    private int destinationPort;
    private Socket socket;
    private DataOutputStream outStream = null;
    private String logName;

    public Sender(String destinationAddress, int destinationPort){
        this.destinationAddress = destinationAddress;
        this.destinationPort = destinationPort;
        logger.setLevel(Level.ALL);
        logName = "Sender (" + this.destinationAddress + " : "+ this.destinationPort + ")";
        logger.log(Level.INFO,logName + " has been initialized");
    }

    @Override
    public void run() {
        boolean isON = true;
        isON = connect();
        isON = activateStream();
    }

    public boolean send(ClientMessage message){
        String x = Serializer.serialize(message);
        try {
            outStream.writeUTF(x);
        } catch (IOException e) {
            //TODO: Manage this error
            return false;
        }
        //TODO: check for the confirmation of message and return it
        return true;
    }

    protected boolean connect(){
        try {
            this.socket = new Socket(this.destinationAddress, this.destinationPort);
            logger.log(Level.INFO,logName + " is connected");
            return true;
        } catch (IOException e) {
            //TODO: throw warning
            return false;
        }
    }

    protected boolean activateStream(){
        try {
            this.outStream = new DataOutputStream(socket.getOutputStream());
            logger.log(Level.INFO,logName + " has created an output stream");
            return true;
        } catch (IOException e) {
            //TODO: throw warning
            return false;
        }
    }

    protected boolean close(){
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
