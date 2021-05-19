package it.polimi.ingsw.network.serverNetwork;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class serverSenderSocket {
    private String hostname;
    private int port;
    private Socket socket;
    /**
     * TO COMMENT Set parameters for the Client Sender Socket
     */
    public void setClientSenderSocket(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
    /**
     * TO COMMENT Open connection
     */
    public void connect() {
        try {
            this.socket = new Socket(this.hostname, this.port);
        } catch (IOException e) {
            System.err.println("Socket: connection failed");
            //trying to close the socket in case of failed connection
            try {
                this.socket.close();
            } catch (IOException ioException) {
                System.err.println("Socket: termination failed");
            }
        }
    }
    public boolean sendMessage(String message) {
        DataOutputStream stream = null;
        try {
            stream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Socket: stream creation failed");
        }
        try {
            if (stream != null) {
                stream.writeUTF(message);
            }
        } catch (IOException e) {
            System.err.println("Socket: message sending failed");
        }
        // check for the confirmation of message and return boolean
        return true;
    }
    public void close(){
        try {
            this.socket.close();
        } catch (IOException ioException) {
            System.err.println("Socket: termination failed");
        }
    }
}