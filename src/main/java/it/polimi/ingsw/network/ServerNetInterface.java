package it.polimi.ingsw.network;

import it.polimi.ingsw.Server;
import it.polimi.ingsw.network.components.Listener;
import it.polimi.ingsw.network.components.ListenerOccupiedExeption;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;

public class ServerNetInterface {
    private static int port;
    private static int maxSlots;
    private static boolean isON = false;

    private static int activeSlots = 0;
    private static int activeMatches = 0;

    private static ServerSocket serverSocket;

    /**
     * Setter for the Server Port.
     *
     * @author Lancini Davide
     */
    public static void setPort(int listenerPort) throws ListenerOccupiedExeption {
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
            //TODO start missing threads
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
     * Main Server Method
     *
     *
     * @author Lancini Davide
     */
    public static void startServer(){
        if(isON){
            Server.logger.log(Level.WARNING,"serverMain>startMain> The server is already ON");
        }else{
            try {
                serverSocket = new ServerSocket(port);
                isON = true;
            } catch (IOException e) {
                Server.logger.log(Level.WARNING,"serverMain>startMain> Opening failed");
                isON = false;
            }
        }

        int i;
        for(i=0;i<maxSlots;i++){
            //TODO: create an empty controller and pass serverSocket
        }
    }

    /**
     * Stop Listener without save (for now).
     *
     *
     * @author Lancini Davide
     */
    public static void stopServer(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            Server.logger.log(Level.WARNING,"serverMain>stopMain> Closing failed");
        }
    }
}
