package it.polimi.ingsw.network.components;

import it.polimi.ingsw.network.ClientMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Sender implements Runnable{
    private String destinationAddress;
    private int destinationPort;
    private Socket socket;
    private DataOutputStream outStream = null;

    public Sender(String destinationAddress, int destinationPort){
        this.destinationAddress = destinationAddress;
        this.destinationPort = destinationPort;
        //TODO: throw info
    }

    @Override
    public void run() {
        boolean isON = true;
        isON = connect();
        isON = activateStream();
        while(isON){
            //TODO: keep sending ping and update isON
        }
        isON = close();
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
            //TODO: throw info
            return true;
        } catch (IOException e) {
            //TODO: throw warning
            return false;
        }
    }

    protected boolean activateStream(){
        try {
            this.outStream = new DataOutputStream(socket.getOutputStream());
            //TODO: throw info
            return true;
        } catch (IOException e) {
            //TODO: throw warning
            return false;
        }
    }

    protected boolean close(){
        try {
            this.socket.close();
            return false;
        } catch (IOException ioException) {
            //TODO: Manage this error
            return true;
        }
    }
}
