package it.polimi.ingsw.network.serverNetwork;

public class ListenerOccupiedExeption extends Exception {
    public ListenerOccupiedExeption(String errorMessage) {
        super(errorMessage);
    }
}
