package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.network.components.ListenerOccupiedException;
import it.polimi.ingsw.view.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * Class ServerNetworkController
 * @author Group 12
 */
public class ServerNetworkController {
    private static int port;
    private static int maxSlots;
    public static boolean isON = false;

    private static int activeSlots = 0;

    private static ServerSocket serverSocket;

    /**
     * Definition for a small thread that starts single players
     *
     */
    private static final Thread connection = new Thread() {
        @Override
        public void run() {
            while (isON && !isInterrupted()) {
                if(activeSlots < maxSlots ){
                    Player emptyPlayer = new Player(serverSocket);
                    emptyPlayer.start();
                }
            }
        }
    };

    /**
     * Setter for the Server Port.
     */
    public static void setPort(int listenerPort) throws ListenerOccupiedException {
        if(isON){
            Log.logger.warning("Listener occupied, the port cannot be modified");
            throw new ListenerOccupiedException("ERROR");
        }else{
            port = listenerPort;
            Log.logger.fine("Port received and applied: "+port);
        }
    }

    /**
     * Setter for the Server Max Slot.
     */
    public static void setMaxSlots(int listenerMaxSlot) throws InstantiationException {
        if(isON & activeSlots > listenerMaxSlot){
            Log.logger.warning("Slots requests exceed Max Slots");
            throw new InstantiationException();
        }else{
            maxSlots = listenerMaxSlot;
            Log.logger.fine("Max Slots received and applied: "+maxSlots);
        }
    }

    /**
     * Getter for the Server Port.
     */
    public static int getPort(){
        return port;
    }

    /**
     * Getter for the Server Max Slot.
     */
    public static int getMaxSlots() {
        return maxSlots;
    }

    /**
     * Getter for the Status
     */
    public static boolean getStatus(){ return isON;}

    /**
     * Main Server Method
     */
    public static void startServer(){
        if(isON){
            Log.logger.warning("The server is already ON");
        }else{
            try {
                serverSocket = new ServerSocket(port);
                Log.logger.info("Father Socket Created on: "+port);
                isON = true;
            } catch (IOException e) {
                Log.logger.warning("Opening failed");
                isON = false;
            }
        }
        if(connection.isInterrupted()){
            connection.checkAccess();
        }else{
            connection.start();
        }
    }

    /**
     * Stop the entire server network, every components will be shutdown without saving
     */
    public static void stopServer(){
        try {
            isON = false; //mark the Network OFF
            Socket emptyPlayerResolver = new Socket("localhost", port);
            sleep(1000);
            serverSocket.close(); //close the Main Socket
            connection.interrupt(); //Interrupt the connection creator
        } catch (IOException | InterruptedException e) {
            Log.logger.warning("Closing failed");
        }
    }

    /**
     * Increase the Active Slots Count when a new connection is made
     */
    public static void addPlayer(){
        activeSlots++;
        Log.logger.info("Slots: " + activeSlots + "/" + maxSlots);
    }

    /**
     * Decrease the Active Slots Count when a connection is lost
     */
    public static void removePlayer(){
        activeSlots--;
        Log.logger.info("Slots: " + activeSlots + "/" + maxSlots);
    }
}