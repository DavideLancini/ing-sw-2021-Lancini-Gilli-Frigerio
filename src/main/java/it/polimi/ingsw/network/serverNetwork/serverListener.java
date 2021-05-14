package it.polimi.ingsw.network.serverNetwork;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverListener {
    private static int port;
    private static int slot;
    private Socket socket;
    private ServerSocket server;
    private DataInputStream stream;
    /**
     * TO COMMENT
     */
    public static void setListenerParameters(int port, int slot) {
        serverListener.port = port;
        serverListener.slot = slot;
    }
    /**
     * TO COMMENT Open the Listener and start recording
     */
    public void initializeListener(){
        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Listener: opening failed");
        }
        try {
            socket = this.server.accept();
        } catch (IOException e) {
            System.err.println("Listener: waiting failed");
        }
        //i don't think is a good idea to keep the stream always in record mode
        try {
            this.stream = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Listener: stream creation failed");
        }
    }
    /**
     * TO COMMENT take the first message in the recording pile
     */
    public String listen(){
        String message = null;
        try
        {
            message = this.stream.readUTF();
        }
        catch(IOException i)
        {
            System.err.println("Listener: message collection failed");
        }
        return message;
    }
    /**
     * TO COMMENT Close the listener (but not the server)
     */
    public void closeListener(){
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Listener: termination failed");
        }
    }
}
