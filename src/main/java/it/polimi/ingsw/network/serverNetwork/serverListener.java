package it.polimi.ingsw.network.serverNetwork;

import it.polimi.ingsw.Server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverListener {
    private static boolean isON = false;
    private static int port;
    private static int maxSlot;
    private static Socket socket;
    private static ServerSocket server;
    private static DataInputStream stream;
    /**
     * TO COMMENT
     */
    public static void setListenerParameters(int listenerPort, int listenerMaxSlot){
        if(isON){
            if (Server.logLevel > 0) { System.out.println("WARNING: ListenerOccupied, parameters are locked if the Listener is Running"); }
        }else{
            port = listenerPort;
            maxSlot = listenerMaxSlot;
        }
    }
    public static void setPort(int listenerPort){
        if(isON){
            if (Server.logLevel > 0) { System.out.println("WARNING: ListenerOccupied, parameters are locked if the Listener is Running"); }
        }else{
            port = listenerPort;
        }
    }
    public static void setMaxSlot(int listenerMaxSlot){
        if(isON){
            //check if the slot requested are enough to keep every connection open
        }else{
            maxSlot = listenerMaxSlot;
        }
    }
    /**
     * TO COMMENT Open the Listener and start recording
     */
    public static void startListener(){
        if(isON){
            if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) ListenerOccupied, listener is already ON"); }
        }else{
            try {
                server = new ServerSocket(port);
            } catch (IOException e) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) IOException, opening failed"); }
                isON = false;
            }
            try {
                socket = server.accept();
            } catch (IOException e) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) IOException, waiting failed"); }
                isON = false;
            }
            try {
                stream = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
                isON = true; // now the Listener is considered Open
            } catch (IOException e) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) IOException, stream opening failed"); }
                isON = false;
            }
        }
    }
    /**
     * TO COMMENT take the first message in the recording pile
     */
    public String listen(){
        String message = null;
        try
        {
            message = stream.readUTF();
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
