package it.polimi.ingsw;

import it.polimi.ingsw.network.ClientNetInterface;
import org.junit.Test;

public class ClientTestMatch {
    @Test
    public void main(){
        ClientNetInterface net = new ClientNetInterface();

        net.setParameters("localhost", 1000, 1001);

        net.connect();

        //TODO: when the login method is working insert HERE

        //TODO: first create match

        //TODO: start game
    }





}