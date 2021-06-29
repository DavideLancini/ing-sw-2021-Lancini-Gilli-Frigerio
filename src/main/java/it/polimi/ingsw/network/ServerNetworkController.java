package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.network.components.ListenerOccupiedException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Class ServerNetworkController
 * @author Group 12
 */
public class ServerNetworkController {
    private static Logger logger;
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
                    Player emptyPlayer = new Player(serverSocket, logger);
                    emptyPlayer.start();
                }
            }
        }
    };

    /**
     * Setter for the Logger
     */
    public static void setLogger(Logger logger) {
        ServerNetworkController.logger = logger;
    }

    /**
     * Setter for the Server Port.
     */
    public static void setPort(int listenerPort) throws ListenerOccupiedException {
        if(isON){
            logger.log(Level.WARNING,"Listener occupied, the port cannot be modified", new ListenerOccupiedException("ERROR"));
        }else{
            port = listenerPort;
            logger.log(Level.FINE,"Port received and applied: "+port);
        }
    }

    /**
     * Setter for the Server Max Slot.
     */
    public static void setMaxSlots(int listenerMaxSlot) throws InstantiationException {
        if(isON & activeSlots > listenerMaxSlot){
            logger.log(Level.WARNING, "Slots requests exceed Max Slots",new InstantiationException());
        }else{
            maxSlots = listenerMaxSlot;
            logger.log(Level.FINE, "Max Slots received and applied: "+maxSlots);
        }
    }

    /**
     * Getter for the Logger.
     */
    public static Logger getLogger() {
        return logger;
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
            logger.log(Level.WARNING,"The server is already ON");
        }else{
            try {
                serverSocket = new ServerSocket(port);
                logger.log(Level.INFO, "Father Socket Created on: "+port);
                isON = true;
            } catch (IOException e) {
                logger.log(Level.WARNING,"Opening failed");
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
            logger.log(Level.WARNING,"Closing failed");
        }
    }

    /**
     * Increase the Active Slots Count when a new connection is made
     */
    public static void addPlayer(){
        activeSlots++;
        logger.info("Slots: " + activeSlots + "/" + maxSlots);
    }

    /**
     * Decrease the Active Slots Count when a connection is lost
     */
    public static void removePlayer(){
        activeSlots--;
        logger.info("Slots: " + activeSlots + "/" + maxSlots);
    }
}