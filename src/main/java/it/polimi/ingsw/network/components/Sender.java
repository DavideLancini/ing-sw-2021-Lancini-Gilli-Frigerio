package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Sender extends Thread {
    private String address;
    private int port;
    private Socket socket;
    private DataOutputStream stream = null;

    public Sender(String address, int port){
        this.address = address;
        this.port = port;
    }

    public boolean connect(){
        // opening sender
        try {
            this.socket = new Socket(this.address, this.port);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public void run(){
        // activate output stream
        try {
            this.stream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            //TODO close thread
        }

        // keep thread running
        while(true){
            //TODO Timeout connection, close thread
        }
    }

    public boolean send(Message message) {
        String x = Serializer.serialize(message);

        try {
            stream.writeUTF(x);
        } catch (IOException e) {
            //TODO Menage this error
        }
        // check for the confirmation of message and return boolean
        return true;
    }
    public void close(){
        try {
            this.socket.close();
        } catch (IOException ioException) {
            //TODO Menage this error
        }
    }
}
