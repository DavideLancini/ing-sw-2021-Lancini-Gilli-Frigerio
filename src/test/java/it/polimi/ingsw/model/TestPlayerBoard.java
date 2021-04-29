package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertSame;

public class TestPlayerBoard {
    @Test
    public void testAddDevCard(){
        PlayerBoard pb = new PlayerBoard();
        Resource[] cost = {Resource.COIN, Resource.STONE};
        Production production = new Production(cost, cost);
        DevCard newCard = new DevCard(Level.ONE, CardColor.BLUE, 5, cost, production);

        try {
            pb.addDevCard(newCard, 0);
        }
        catch (Exception e){}

        assertSame(newCard, pb.getDevCard(0,0));

        DevCard newCard2 = new DevCard(Level.TWO, CardColor.GREEN, 7, cost, production);

        try {
            pb.addDevCard(newCard2, 0);
        }
        catch (Exception e){}

        assertSame(newCard2, pb.getDevCard(0,1));
        assertSame(newCard, pb.getDevCard(0,0));

    }
}
