package it.polimi.ingsw.controller;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

public class GameTest{

    ServerSocket fatherSocket;
    {
        try {
            fatherSocket = new ServerSocket();
        } catch (IOException e) {
            //TODO
        }
    }

    Player[] pbs= new Player[]{new Player(fatherSocket)};

    @Test
    public void Game() throws Exception {
        Game game= new Game(pbs);

    }

}