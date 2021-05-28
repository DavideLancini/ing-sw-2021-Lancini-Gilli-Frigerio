package it.polimi.ingsw;

import it.polimi.ingsw.network.components.ListenerOccupiedExeption;
import it.polimi.ingsw.network.ServerNetInterface;
import org.junit.Test;

public class ServerTestMatch{
    //@Test
    public void main(){
        System.out.println("This is a modified version of the server, the Logger is locked to the Finest Level, " +
                "every commodity features are disabled and the server runs only one match for exactly 4 clients");
        try {
            ServerNetInterface.setMaxSlots(4);
        } catch (InstantiationException e) {
            System.out.println("ERROR: Unreachable Error in this Test");
        }
        try {
            ServerNetInterface.setPort(1000);
        } catch (ListenerOccupiedExeption listenerOccupiedExeption) {
            System.out.println("ERROR: Unreachable Error in this Test");
        }
        ServerNetInterface.startServer();
        while(true){
            // This is a test scenario for the server, the shutdown functionalities (with or without save) are disabled
            // Please manually close this test
        }
    }
}