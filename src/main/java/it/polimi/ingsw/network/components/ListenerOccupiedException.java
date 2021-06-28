package it.polimi.ingsw.network.components;

/**
 * This custom error is Thrown by the Server Main Network Interface to the Server View when the Main Server Listener
 * cannot change parameters because is occupied doing something else as it is on a different thread.
 *
 * @author Group 12
 */
public class ListenerOccupiedException extends Exception {
    public ListenerOccupiedException(String errorMessage) {
        super(errorMessage);
    }
}
