package it.polimi.ingsw.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDownPrice {
    @Test
    public void TestDownPrice(){
        LeaderSale leader = new LeaderSale(0, Resource.COIN, new CardColor[]{CardColor.YELLOW},"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png");
        Resource[] fullcost = new Resource[]{Resource.COIN, Resource.SHIELD, Resource.COIN};

        Resource[] reducedcost = leader.downPrice(fullcost);

        assertSame(2, reducedcost.length);
        assertSame(Resource.SHIELD, reducedcost[0]);
        assertSame(Resource.COIN, reducedcost[1]);


    }

}
