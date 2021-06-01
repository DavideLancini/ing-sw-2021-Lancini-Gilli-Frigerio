package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.DisconnectedException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Listener Component
 *
 * @author Lancini Davide
 */
public class Listener{
    private static Logger listenerLogger = Logger.getLogger("ListenerLogger");

    private Socket listenerSocket;
    private DataInputStream inputStream;
    private String listenerName;

    public Listener(ServerSocket fatherSocket) throws DisconnectedException{
        listenerLogger.setLevel(Level.ALL);
        //Accept connection
        try {
            this.listenerSocket = fatherSocket.accept();
            this.listenerName = "Listener" + getTargetAddress();
            listenerLogger.log(Level.INFO,this.listenerName+" has accepted a connection");
        } catch (IOException error) {
            listenerLogger.log(Level.WARNING,"Cannot accept a connection");
            throw new DisconnectedException("Listener cannot accept connection");
        }
        //Open input stream
        try {
            this.inputStream = new DataInputStream(new BufferedInputStream(this.listenerSocket.getInputStream()));
            listenerLogger.log(Level.INFO,this.listenerName+"has created a stream ");
        } catch (IOException error) {
            listenerLogger.log(Level.WARNING,this.listenerName+"Listener failed to open a stream");
            throw new DisconnectedException("Failed to open a stream");
        }
    }

    public String receive() throws DisconnectedException {
        String rawMessage = "";
        try {
            rawMessage = this.inputStream.readUTF();
            return rawMessage;
        } catch (IOException e) {
            throw new DisconnectedException("Failed to receive");
        }
    }

    public String getTargetAddress(){
        return this.listenerSocket.getRemoteSocketAddress().toString();
    }
}