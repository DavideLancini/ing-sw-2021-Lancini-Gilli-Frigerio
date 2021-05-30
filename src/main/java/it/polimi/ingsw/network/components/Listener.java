package it.polimi.ingsw.network.components;

import it.polimi.ingsw.Server;
import it.polimi.ingsw.network.ClientMessage;

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
public class Listener implements Runnable {
    private ServerSocket fatherSocket = null;
    private Socket listenerSocket = null;
    private DataInputStream inputStream = null;

    public Listener(ServerSocket socket){
        fatherSocket = socket;
    }

    public void run(){
        try {
            listenerSocket = fatherSocket.accept();
        } catch (IOException e) {
            Server.logger.log(Level.WARNING,"serverListenerSocket>run> This Thread cannot accept connections");
            // TODO restart thread HERE
        }

        try {
            inputStream = new DataInputStream(new BufferedInputStream(listenerSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            //Receive
            ClientMessage x = receive();

        }
    }

    public ClientMessage receive(){
        String rawMessage = "";
        try {
            rawMessage = inputStream.readUTF();
        } catch (IOException e) {
            // TODO error message
        }
        return Serializer.deserializeMessage(rawMessage);
    }
}