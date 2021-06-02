package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Reader;
import it.polimi.ingsw.network.ServerNetInterface;
import it.polimi.ingsw.network.components.ListenerOccupiedException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

/**
 * View for the Server Application
 *
 * @author Lancini Davide
 */
public class ServerView {
    private static Logger logger;

    /**
     * Import Logger from the ServerApp and pass it to the Network Interface
     * @author Lancini Davide
     */
    public static void setLogger(Logger logger) {
        ServerView.logger = logger;
        ServerNetInterface.setLogger(logger);
    }

    /**
     * Load all server Parameters from server.properties, in case of failure standard parameters will be loaded.
     * Standard Parameters:
     * - Local Port: 5555
     * - Max Slots: 8
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
            logger.log(Level.CONFIG,"Server.properties successfully loaded from file");
        }
        catch (FileNotFoundException FileNotFoundException) {
            maxSlots = 8;
            port = 5555;
            logger.log(Level.WARNING,"Server.properties not found, loaded standard parameters");
        }
        catch (IOException IOException){
            maxSlots = 8;
            port = 5555;
            logger.log(Level.WARNING,"IO Error, loaded standard parameters");
        }
        logger.log(Level.INFO,"Parameters Loaded: (Port: "+port+") (Max Slots: "+maxSlots+")");
        try {
            ServerNetInterface.setMaxSlots(maxSlots);
            logger.log(Level.CONFIG,"Max Slots successfully applied");
        }
        catch (InstantiationException InstantiationException) {
            logger.log(Level.WARNING,"Active Slots exceed Requested Max Slots");
        }

        try {
            ServerNetInterface.setPort(port);
            logger.log(Level.CONFIG,"Port successfully applied");
        }
        catch (ListenerOccupiedException listenerOccupiedException) {
            logger.log(Level.WARNING,"The Listener is ON, the port cannot be changed");
        }
    }

    /**
     * Print server status and the available commands
     * @author Lancini Davide
     */
    public static String serverMenu() {
        clearConsole();
        if (ServerNetInterface.getStatus()) {
            System.out.println("The server is ON ");
            System.out.println("- Port: "+ ServerNetInterface.getPort());
            System.out.println("- Max Slots: "+ ServerNetInterface.getMaxSlots());
            System.out.println("- Active Games: "+ ServerNetInterface.getActiveGames());
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
     * @author Lancini Davide
     */
    public static void toggleServer() {
        if (ServerNetInterface.getStatus()) {
            ServerNetInterface.stopServer();
            logger.log(Level.INFO, "Server Stopped");
        } else {
            ServerNetInterface.startServer();
            logger.log(Level.INFO, "Server Started");
        }
    }

    /**
     * Edit the standard parameters for the server.
     * Most parameters can be modified with the Server Online.
     * Parameters that need a reboot to be activated will still be saved in the properties file (with no effect)
     * @author Lancini Davide
     */
    public static void editParameters() {
        //TODO editParameters(): for now edit the properties file
    }

    /**
     * Simple feature to keep the console clean, tested on windows, mac os and some linux distros.
     * Only works if the Logger is OFF
     * @author Lancini Davide
     */
    private static void clearConsole(){
        if(logger.getLevel() == Level.OFF){
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.print("\033\143");
                }
            }
            catch (IOException | InterruptedException e1) {
                logger.log(Level.WARNING,"IOException, console not cleaned");
            }
        }
    }
}