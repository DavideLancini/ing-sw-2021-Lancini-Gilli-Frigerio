package it.polimi.ingsw.view.server;

import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.Reader;
import it.polimi.ingsw.network.components.ListenerOccupiedExeption;
import it.polimi.ingsw.network.components.serverMain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

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
    public static void loadParameters() {
        int port;
        int maxSlots;
        try {
            FileInputStream file = new FileInputStream("src/main/resources/server.properties");
            Properties serverProperties = new Properties();
            serverProperties.load(file);
            maxSlots = parseInt(serverProperties.getProperty("maxSlots"));
            port = parseInt(serverProperties.getProperty("port"));
            file.close();
        }
        catch (FileNotFoundException FileNotFoundException) {
            Server.logger.log(Level.WARNING,"ServerView>LoadParameters> Server.properties not found, loaded standard parameters");
            maxSlots = 1000;
            port = 4;
        }
        catch (IOException IOException){
            Server.logger.log(Level.WARNING,"ServerView>LoadParameters> IO Error, loaded standard parameters");
            maxSlots = 1000;
            port = 4;
        }

        try {
            serverMain.setMaxSlots(maxSlots);
        }
        catch (InstantiationException InstantiationException) {
            Server.logger.log(Level.WARNING,"ServerView>LoadParameters> Active Slots exceed Requested Max Slots");
        }

        try {
            serverMain.setPort(port);
        }
        catch (ListenerOccupiedExeption listenerOccupiedExeption) {
            Server.logger.log(Level.WARNING,"ServerView>LoadParameters> The Listener is ON, the port cannot be changed");
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
    public static void toggleServer() {
        if (checkServerActivity()) {
            serverMain.stopMain();
        } else {
            serverMain.startMain();
        }
    }

    /**
     * Edit the standard parameters for the server.
     * Most parameters can be modified with the Server Online.
     * Parameters that need a reboot to be activated will still be saved in the properties file (with no effect)
     *
     * @author Lancini Davide
     */
    public static void editParameters() {
        //TODO editParameters(): for now edit the properties file
    }

    /**
     * Simple feature to keep the console clean, tested on windows, mac os and some linux distros.
     *
     * @author Lancini Davide
     */
    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        }
        catch (IOException | InterruptedException e1) {
            Server.logger.log(Level.WARNING,"ServerView>clearConsole> IOException, console not cleaned");
        }
    }

    /**
     * Check if the sockets are online
     *
     * @return true if both sockets are online
     * @author Lancini Davide
     */
    private static boolean checkServerActivity() {
        return serverMain.getStatus();
    }
}