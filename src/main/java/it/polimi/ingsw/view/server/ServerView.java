package it.polimi.ingsw.view.server;

import it.polimi.ingsw.Server;
import it.polimi.ingsw.network.serverNetwork.ListenerOccupiedExeption;
import it.polimi.ingsw.network.serverNetwork.serverListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Integer.parseInt;

/**
 * Interface for the Server Application
 *
 * @author Lancini Davide
 */
public class ServerView {
    /**
     * Simple feature to keep the console clean, tested on windows, mac os and some linux distros.
     * If the logLevel is set to VERBOSE the Console will NOT be cleaned
     *
     * @author Lancini Davide
     */
    public static void clearConsole(){
        if (Server.logLevel < 2) {
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
                else {
                    System.out.print("\033\143");
                }
            } catch (IOException | InterruptedException e1) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (clearConsole) IOException, console not cleaned"); }
            }
        }
    }

    /**
     * Load preferred port number and max slot for match
     *
     * @warning InstantiationException: Do Nothing
     * @warning ListenerOccupiedException: Do Nothing
     * @warning FileNotFoundException: Load standard parameters
     * @warning IOException: Load standard parameters
     * @author Lancini Davide
     */
    public static void loadParameters() { //TODO CleanUp (It works Though)
        try {
            FileInputStream file = new FileInputStream("src/main/resources/server.properties");
            Properties serverProperties = new Properties();
            serverProperties.load(file);
            try {
                serverListener.setMaxSlots(parseInt(serverProperties.getProperty("slot")));
            } catch (InstantiationException InstantiationException) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) Active Slots exceed Requested Max Slots"); }
            }
            try {
                serverListener.setPort(parseInt(serverProperties.getProperty("port")));
            } catch (ListenerOccupiedExeption listenerOccupiedExeption) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) The Listener is ON, the port cannot be changed"); }
            }
            file.close();
        } catch (FileNotFoundException FileNotFoundException) {
            try {
                serverListener.setMaxSlots(1000);
            } catch (InstantiationException InstantiationException) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) Active Slots exceed Requested Max Slots"); }
            }
            try {
                serverListener.setPort(5);
            } catch (ListenerOccupiedExeption listenerOccupiedExeption) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) The Listener is ON, the port cannot be changed"); }
            }
            if (Server.logLevel > 0) { System.out.println("WARNING: (server.properties) Not Found, loaded standard parameters"); }
        } catch (IOException IOException) {
            try {
                serverListener.setMaxSlots(1000);
            } catch (InstantiationException InstantiationException) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) Active Slots exceed Requested Max Slots"); }
            }
            try {
                serverListener.setPort(5);
            } catch (ListenerOccupiedExeption listenerOccupiedExeption) {
                if (Server.logLevel > 0) { System.out.println("WARNING: (serverListener) The Listener is ON, the port cannot be changed"); }
            }
            if (Server.logLevel > 0) { System.out.println("WARNING: (server.properties) IO Error, loaded standard parameters"); }
        }
    }

    /**
     * Print server status and the available commands
     *
     * @author Lancini Davide
     */
    public static void serverStatus() {
        if (checkServerActivity()) {
            System.out.println("The server is ON");
            // TODO print server parameters (hostname, port, max slots, open slots)
            System.out.println("1. Stop Server");
        } else {
            System.out.println("The server is OFF");
            System.out.println("1. Start Server");
        }
        System.out.println("2. Edit server parameters");
        System.out.println("3. Exit");
    }

    /**
     * Check if the sockets are online
     *
     * @return true if both sockets are online, if only one is online the server will be considered offline
     * @author Lancini Davide
     */
    public static boolean checkServerActivity() {
        // TODO check if the socket are (both) online
        return true; // temporary
    }

    /**
     * Take input from the user and do what is asked
     *
     * @author Lancini Davide
     */
    public static void takeAction() {
        String action;
        action = System.console().readLine(); //TODO: input does not work
        switch (action) {
            case "1":
                if(checkServerActivity()){
                    serverListener.startListener();
                }
                break;
            case "2":
                // edit parameters
                break;
            case "3":
                // close without save
                break;
            default:
                // error in input, just refresh
                break;
        }
    }
}
