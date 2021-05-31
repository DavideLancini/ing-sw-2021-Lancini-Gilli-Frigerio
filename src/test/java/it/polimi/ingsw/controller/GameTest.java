package it.polimi.ingsw.controller;
import org.junit.Test;

import java.io.FileNotFoundException;

public class GameTest{
    Player[] pbs= new Player[]{new Player()};

    @Test
    public void Game() throws Exception {
        Game game= new Game(pbs);

    }

}