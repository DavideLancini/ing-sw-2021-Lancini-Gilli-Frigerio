package it.polimi.ingsw;

import it.polimi.ingsw.network.components.ListenerOccupiedExeption;
import it.polimi.ingsw.network.serverNetInterface;
import org.junit.Test;

public class ServerTestMatch{
    @Test
    public void main(){
        try {
            serverNetInterface.setMaxSlots(4);
        } catch (InstantiationException e) {
            System.out.println("ERROR");
        }
        try {
            serverNetInterface.setPort(1000);
        } catch (ListenerOccupiedExeption listenerOccupiedExeption) {
            System.out.println("ERROR");
        }
        serverNetInterface.startServer();
        while(true){ }
        // serverNetInterface.stopServer();
    }
}