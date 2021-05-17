package it.polimi.ingsw.network.serverNetwork;

import it.polimi.ingsw.Server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverListener {
    private static int port;
    private static int maxSlots;
    private static boolean isON = false;
    private static final int activeSlots = 0;
    private static Socket socket;
    private static ServerSocket server;
    private static DataInputStream stream;
    /**
     * Setter for the Server Port.
     *
     *
     * @author Lancini Davide
     */
    public static void setPort(int listenerPort) throws ListenerOccupiedExeption{
        if(isON){
            throw new ListenerOccupiedExeption("ERROR: Listener occupied, the port cannot be modified");
        }else{
            port = listenerPort;
        }
    }
    public static int getPort(){
        return port;
    }
    public static void setMaxSlots(int listenerMaxSlot) throws InstantiationException {
        if(isON & activeSlots > listenerMaxSlot){
            throw new InstantiationException();
        }else{
            maxSlots = listenerMaxSlot;
        }
    }
    public static int getMaxSlots() {
        return maxSlots;
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
