package it.polimi.ingsw.view.server;

import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.Reader;
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
    public static String serverMenu() {
        clearConsole();
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
        return Reader.in.nextLine();
    }

    /**
     * Toggle Server ON/OFF
     *
     * @author Lancini Davide
     */
    public static void toggleServer(){
        if(checkServerActivity()){
            startServer();
        }else{
            stopServer();
        }
    }

    /**
     * Edit the standard parameters for the server.
     * Most parameters can be modified with the Server Online.
     * Parameters that need a reboot to be activated will still be saved in the properties file (with no effect)
     *
     * @author Lancini Davide
     */
    public static void editParameters(){
        //TODO editParameters(): for now edit the properties file
    }

    /**
     * Simple feature to keep the console clean, tested on windows, mac os and some linux distros.
     * If the logLevel is set to VERBOSE the Console will NOT be cleaned
     *
     * @author Lancini Davide
     */
    private static void clearConsole(){
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
     * Check if the sockets are online
     *
     * @return true if both sockets are online
     * @author Lancini Davide
     */
    private static boolean checkServerActivity() {
        return serverListener.getStatus();
    }

    /**
     * Start Server
     *
     * @author Lancini Davide
     */
    private static void startServer(){
        //TODO
    }

    /**
     * Stop Server
     *
     * @author Lancini Davide
     */
    private static void stopServer(){
        //TODO
    }
}