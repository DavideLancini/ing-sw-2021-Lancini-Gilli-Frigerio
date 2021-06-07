package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertSame;

public class TestPlayerBoard {
    PlayerBoard pb = new PlayerBoard();
    Resource[] cost = {Resource.COIN, Resource.STONE};
    Production production = new Production(cost, cost);
    DevCard newCard = new DevCard(Level.ONE, CardColor.BLUE, 5, cost, production);
    DevCard newCard2 = new DevCard(Level.TWO, CardColor.GREEN, 7, cost, production);
    @Test
    public void testAddDevCard(){

        try {
            pb.addDevCard(newCard, 0);
        }
        catch (Exception e){}

        assertSame(newCard, pb.getDevCard(0,0));



        try {
            pb.addDevCard(newCard2, 0);
        }
        catch (Exception e){}

        assertSame(newCard2, pb.getDevCard(0,1));
        assertSame(newCard, pb.getDevCard(0,0));

    }
    @Test
    public void testPlayerView(){
        try {
            pb.addDevCard(newCard, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pb.addDevCard(newCard, 0);
        }
        catch (Exception e){}
        try {
            pb.addDevCard(newCard2, 0);
        }
        catch (Exception e){}
        pb.addFaith(23);
        System.out.println( pb.playerBoardView("testPlayer"));
    }
}
