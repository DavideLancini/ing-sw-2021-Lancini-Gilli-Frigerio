package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Reader;
import it.polimi.ingsw.network.ServerNetworkController;
import it.polimi.ingsw.network.components.ListenerOccupiedException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import static java.lang.Integer.parseInt;

/**
 * View for the Server Application
 *
 * @author Group 12
 */
@SuppressWarnings("CommentedOutCode")
public class ServerView {

    /**
     * Load all server Parameters from server.properties, in case of failure standard parameters will be loaded.
     * Standard Parameters:
     * - Local Port: 5555
     * - Max Slots: 8
     * warning InstantiationException: Do Nothing
     * warning ListenerOccupiedException: Do Nothing
     * warning FileNotFoundException: Load standard parameters
     * warning IOException: Load standard parameters
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
            Log.logger.config("Server.properties successfully loaded from file");
        }
        catch (FileNotFoundException FileNotFoundException) {
            maxSlots = 8;
            port = 5555;
            Log.logger.warning("Server.properties not found, loaded standard parameters");
        }
        catch (IOException IOException){
            maxSlots = 8;
            port = 5555;
            Log.logger.warning("IO Error, loaded standard parameters");
        }
        Log.logger.info("Parameters Loaded: (Port: "+port+") (Max Slots: "+maxSlots+")");
        try {
            ServerNetworkController.setMaxSlots(maxSlots);
            Log.logger.config("Max Slots successfully applied");
        }
        catch (InstantiationException InstantiationException) {
            Log.logger.warning("Active Slots exceed Requested Max Slots");
        }

        try {
            ServerNetworkController.setPort(port);
            Log.logger.config("Port successfully applied");
        }
        catch (ListenerOccupiedException listenerOccupiedException) {
            Log.logger.warning("The Listener is ON, the port cannot be changed");
        }
    }

    /**
     * Print server status and the available commands
     */
    public static String serverMenu() {
        clearConsole();
        if (ServerNetworkController.getStatus()) {
            System.out.println("The server is ON ");
            System.out.println(" - Port: "+ ServerNetworkController.getPort());
            System.out.println(" - Max Slots: "+ ServerNetworkController.getMaxSlots());
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
     */
    public static void toggleServer() {
        if (ServerNetworkController.getStatus()) {
            ServerNetworkController.stopServer();
            Log.logger.info("Server Stopped");
        } else {
            ServerNetworkController.startServer();
            Log.logger.info("Server Started");
        }
    }

    /*
      Edit the standard parameters for the server.
      Most parameters can be modified with the Server Online.
      Parameters that need a reboot to be activated will still be saved in the properties file (with no effect)
     */
    /*public static void editParameters() {
        //editParameters(): for now edit the properties file
    }*/

    /**
     * Simple feature to keep the console clean, tested on windows, mac os and some linux distros.
     * Only works if the Logger is OFF
     */
    private static void clearConsole(){
        if(Log.logger.getLevel() == Level.OFF){
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.print("\033\143");
                }
            }
            catch (IOException | InterruptedException e1) {
                Log.logger.warning("IOException, console not cleaned");
            }
        }
    }
}