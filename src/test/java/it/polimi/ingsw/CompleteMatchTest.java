package it.polimi.ingsw;

import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.ServerNetInterface;
import it.polimi.ingsw.network.components.ListenerOccupiedException;
import org.junit.Test;

public class CompleteMatchTest {
    @Test
    public void main(){
        Runnable server = new Runnable(){
            @Override
            public void run(){
                boolean isEnded = false;
                System.out.println("This is a modified version of the server, the Logger is locked to the Finest Level, " +
                        "every commodity features are disabled and the server runs only one match for exactly 4 clients");
                try {
                    ServerNetInterface.setMaxSlots(4);
                } catch (InstantiationException e) {
                    System.out.println("ERROR: Unreachable Error in this Test");
                }
                try {
                    ServerNetInterface.setPort(1000);
                } catch (ListenerOccupiedException listenerOccupiedExeption) {
                    System.out.println("ERROR: Unreachable Error in this Test");
                }
                ServerNetInterface.startServer();
                while(!isEnded){ //WARNING: do not simplify isEnded
                    //TODO: wait for the match to end
                }
                ServerNetInterface.stopServer();
            }
        };

        Runnable client1 = new Runnable(){
            @Override
            public void run(){
                ClientNetInterface net = new ClientNetInterface();
                net.setParameters("localhost", 1000, 1001);
                try {
                    net.connect();
                } catch (DisconnectedException e) {
                    e.printStackTrace();
                }
                //TODO: when the login method is working insert HERE
                //TODO: create match
                //TODO: start game
            }
        };
        Runnable client2 = new Runnable(){
            @Override
            public void run(){
                ClientNetInterface net = new ClientNetInterface();
                net.setParameters("localhost", 1000, 1002);
                try {
                    net.connect();
                } catch (DisconnectedException e) {
                    e.printStackTrace();
                }
                //TODO: when the login method is working insert HERE
                //TODO: start game
            }
        };
        Runnable client3 = new Runnable(){
            @Override
            public void run(){
                ClientNetInterface net = new ClientNetInterface();
                net.setParameters("localhost", 1000, 1003);
                try {
                    net.connect();
                } catch (DisconnectedException e) {
                    e.printStackTrace();
                }
                //TODO: when the login method is working insert HERE
                //TODO: start game
            }
        };
        Runnable client4 = new Runnable(){
            @Override
            public void run(){
                ClientNetInterface net = new ClientNetInterface();
                net.setParameters("localhost", 1000, 1004);
                try {
                    net.connect();
                } catch (DisconnectedException e) {
                    e.printStackTrace();
                }
                //TODO: when the login method is working insert HERE
                //TODO: start game
            }
        };
    }

}
