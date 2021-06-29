package it.polimi.ingsw;

import it.polimi.ingsw.view.Manager;
import it.polimi.ingsw.view.gui.menus.GUIActionManager;

/**
 * Client App
 */
public class ClientGUI extends Client{
    private static final Manager manager = new GUIActionManager();

    public static void main(String[] args) {
        Client.run("gui", args);
    }

}

//Logger Levels
//Level.OFF     -> Nothing is logged
//Level.SEVERE  -> Fatal Error
//Level.WARNING -> Big Error
//Level.INFO    -> Important Messages that should always be in the console
//Level.CONFIG  -> Important Messages for debug
//Level.FINE    -> Recoverable Failure
//Level.FINER   -> Logging calls for entering, returning, or throwing an exception
//Level.FINEST  -> Highly detailed tracking
//Level.ALL     -> Everything is logged