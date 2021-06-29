package it.polimi.ingsw.controller;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class GameTest{

    private static Logger logger = Logger.getLogger("Test Logger");

    ServerSocket fatherSocket;
    {
        try {
            fatherSocket = new ServerSocket();
        } catch (IOException ignored) {
        }
    }

    Player[] pbs= new Player[]{new Player(fatherSocket, logger)};

    @Test
    public void Game() throws Exception {
        Game game= new Game(pbs);

    }

}