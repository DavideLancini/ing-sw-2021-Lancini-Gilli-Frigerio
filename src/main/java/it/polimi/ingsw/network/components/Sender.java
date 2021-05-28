package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Sender{
    private String destinationAddress;
    private int destinationPort;
    private Socket socket;
    private DataOutputStream outStream = null;

    public Sender(String destinationAddress, int destinationPort){
        this.destinationAddress = destinationAddress;
        this.destinationPort = destinationPort;
        //TODO: throw info
    }

    public boolean connect(){
        // opening sender
        try {
            this.socket = new Socket(this.destinationAddress, this.destinationPort);
            //TODO: throw info
            return true;
        } catch (IOException e) {
            //TODO: throw warning
            return false;
        }
    }

    public boolean activateStream(){
        // activate output stream
        try {
            this.outStream = new DataOutputStream(socket.getOutputStream());
            //TODO: throw info
            return true;
        } catch (IOException e) {
            //TODO: throw warning
            return false;
        }
    }

    public boolean send(Message message){
        String x = Serializer.serialize(message);

        try {
            outStream.writeUTF(x);
        } catch (IOException e) {
            //TODO: Manage this error
            return false;
        }
        //TODO: check for the confirmation of message and return boolean
        return true;
    }
    public boolean close(){
        try {
            this.socket.close();
            return true;
        } catch (IOException ioException) {
            //TODO: Manage this error
            return false;
        }
    }
}
