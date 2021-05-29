package it.polimi.ingsw.network;

import it.polimi.ingsw.network.components.ListenerOccupiedExeption;
import org.junit.Assert;
import org.junit.Test;
/**
 * Network Testing
 *
 * @author Lancini Davide
 */
public class NetworkTest {
    /*
    @Test
    public void testSimpleConnection(){

        //Start a Threaded Server

        Runnable server = new Runnable(){
            @Override
            public void run(){
                boolean isEnded = false;
                try {
                    ServerNetInterface.setMaxSlots(1);
                } catch (InstantiationException e) {
                    System.out.println("ERROR: Unreachable Error in this Test");
                }
                try {
                    ServerNetInterface.setPort(1000);
                } catch (ListenerOccupiedExeption listenerOccupiedExeption) {
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
                net.connect();
                Message x = null;

                net.send(x);



            }
        };

        Assert.assertNull(server);


    }



    @Test
    public void testLogin{
        //TODO
    }

    @Test
    public void testAllMessages{
        //TODO
    }

    @Test
    public void testClientOverflow{
        //TODO
    }

    @Test
    public void testServer10000ActiveThreads{
        //TODO
    }

     */
}
