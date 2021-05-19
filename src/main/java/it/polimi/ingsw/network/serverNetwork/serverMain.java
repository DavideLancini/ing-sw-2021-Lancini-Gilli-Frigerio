package it.polimi.ingsw.network.serverNetwork;

import it.polimi.ingsw.Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverMain {
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

    /**
     * Getter for the Server Port.
     *
     *
     * @author Lancini Davide
     */
    public static int getPort(){
        return port;
    }

    /**
     * Setter for the Server Max Slot.
     *
     *
     * @author Lancini Davide
     */
    public static void setMaxSlots(int listenerMaxSlot) throws InstantiationException {
        if(isON & activeSlots > listenerMaxSlot){
            throw new InstantiationException();
        }else{
            maxSlots = listenerMaxSlot;
        }
    }

    /**
     * Getter for the Server Max Slot.
     *
     *
     * @author Lancini Davide
     */
    public static int getMaxSlots() {
        return maxSlots;
    }

    /**
     * Getter for the Status
     *
     *
     * @author Lancini Davide
     */
    public static boolean getStatus(){ return isON;}

    /**
     * Main Listener Method
     *
     *
     * @author Lancini Davide
     */
    public static void startMain(){
        if(isON){
            if (Server.logLevel > 0) { System.out.println("WARNING: (serverMain) The server is already ON"); }
        }else{
            try {
                server = new ServerSocket(port);
            } catch (IOException e) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) IOException, opening failed"); }
                isON = false;
            }
        }

        serverListenerSocket a = new serverListenerSocket(server);

        int i;
        for(i=0;i<maxSlots;i++){
            Thread t = new Thread(a);
            t.start();
        }

    }

    /**
     * Stop Listener without save (for now)
     *
     *
     * @author Lancini Davide
     */
    public static void stopMain(){
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Listener: termination failed");
        }
    }
}
