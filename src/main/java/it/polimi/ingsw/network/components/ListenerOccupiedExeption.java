package it.polimi.ingsw.network.components;

public class ListenerOccupiedExeption extends Exception {
    public ListenerOccupiedExeption(String errorMessage) {
        super(errorMessage);
    }
}
