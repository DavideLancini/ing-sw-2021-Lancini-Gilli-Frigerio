package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.network.components.ListenerOccupiedException;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerNetInterface {
    private static Logger netLogger = Logger.getLogger("ServerNetInterfaceLogger");
    private static int port;
    private static int maxSlots;
    private static boolean isON = false;

    private static int activeSlots = 0;
    private static int activeGames = 0;

    private static ServerSocket serverSocket;

    /**
     * Setter for the Server Port.
     *
     * @author Lancini Davide
     */
    public static void setPort(int listenerPort) throws ListenerOccupiedException {
        if(isON){
            throw new ListenerOccupiedException("ERROR: Listener occupied, the port cannot be modified");
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
     * @author Lancini Davide
     */
    public static boolean getStatus(){ return isON;}

    public static int getActiveGames() {
        return activeGames;
    }

    /**
     * Main Server Method
     *
     *
     * @author Lancini Davide
     */
    public static void startServer(){
        if(isON){
            netLogger.log(Level.WARNING,"serverMain>startMain> The server is already ON");
        }else{
            try {
                serverSocket = new ServerSocket(port);
                isON = true;
            } catch (IOException e) {
                netLogger.log(Level.WARNING,"serverMain>startMain> Opening failed");
                isON = false;
            }
        }

        //In a thread:
        Runnable connection = new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < maxSlots; i++) {
                    Player x = new Player(serverSocket);
                }
                //TODO: create an empty player and pass serverSocket
            }
        };
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
            isON = false;
        } catch (IOException e) {
            netLogger.log(Level.WARNING,"serverMain>stopMain> Closing failed");
        }
    }
}