package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class TestPlayerBoard {
    final PlayerBoard pb = new PlayerBoard();
    final Resource[] cost = {Resource.COIN, Resource.STONE};
    final Production production = new Production(cost, cost);
    final DevCard newCard = new DevCard(Level.ONE, CardColor.BLUE, 5, cost, production,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png");
    final DevCard newCard2 = new DevCard(Level.TWO, CardColor.GREEN, 7, cost, production,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png");
    @Test
    public void testAddDevCard(){

        try {
            pb.addDevCard(newCard, 0);
        }
        catch (Exception ignored){}

        assertSame(newCard, pb.getDevCard(0,0));



        try {
            pb.addDevCard(newCard2, 0);
        }
        catch (Exception ignored){}

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
        catch (Exception ignored){}
        try {
            pb.addDevCard(newCard2, 0);
        }
        catch (Exception ignored){}
        pb.addFaith(23);
        System.out.println( pb.playerBoardView("testPlayer"));
    }
}
