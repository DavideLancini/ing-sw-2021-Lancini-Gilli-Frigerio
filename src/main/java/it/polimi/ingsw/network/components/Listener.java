package it.polimi.ingsw.network.components;

import it.polimi.ingsw.Server;
import it.polimi.ingsw.network.ClientMessage;
import it.polimi.ingsw.network.DisconnectedException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 * Listener Component
 *
 * @author Lancini Davide
 */
public class Listener{
    private ServerSocket fatherSocket = null;
    private Socket listenerSocket = null;
    private DataInputStream inputStream = null;

    public Listener(ServerSocket socket) throws DisconnectedException{
        fatherSocket = socket;
        try {
            listenerSocket = fatherSocket.accept();
        } catch (IOException e) {
            Server.logger.log(Level.WARNING,"serverListenerSocket>run> This Thread cannot accept connections");
            // TODO: super failed connection
        }
        try {
            inputStream = new DataInputStream(new BufferedInputStream(listenerSocket.getInputStream()));
        } catch (IOException e) {
            throw new DisconnectedException("Failed to open a stream");
        }
    }

    public ClientMessage receive() throws DisconnectedException {
        String rawMessage = "";
        try {
            rawMessage = inputStream.readUTF();
        } catch (IOException e) {
            throw new DisconnectedException("Failed to receive");
        }
        return Serializer.deserializeMessage(rawMessage);
    }
}