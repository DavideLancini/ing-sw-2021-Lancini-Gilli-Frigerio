package it.polimi.ingsw.psp12.network;

public class DisconnectedException extends Exception {
    public DisconnectedException(String errorMessage) {
        super(errorMessage);
    }
}