package it.polimi.ingsw.psp12.controller;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

public class GameTest{

    ServerSocket fatherSocket;
    {
        try {
            fatherSocket = new ServerSocket();
        } catch (IOException ignored) {
        }
    }

    final Player[] pbs= new Player[]{new Player(fatherSocket)};

    @Test
    public void Game(){
        new Game(pbs);
    }

}