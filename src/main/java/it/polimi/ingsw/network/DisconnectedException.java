package it.polimi.ingsw.network;

public class DisconnectedException extends Exception {
    public DisconnectedException(String errorMessage) {
        super(errorMessage);
    }
}