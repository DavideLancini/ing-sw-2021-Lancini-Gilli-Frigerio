package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DevCardTest {
    final Resource[] r1= {Resource.SHIELD,Resource.SERVANT,Resource.SHIELD,Resource.SHIELD};
    final Resource[] r2= {Resource.STONE,Resource.STONE};
    final Resource[] r3= {Resource.SHIELD,Resource.FAITH,Resource.FAITH};
    final Production p1= new Production(r2,r3);
    final DevCard d1= new DevCard(Level.TWO,CardColor.BLUE,9,r1,p1,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png");
    @Test
    public void getProduction() {
        assertSame(p1,d1.getProduction());
    }

    @Test
    public void devCardView() {
        System.out.println(d1.devCardView());
    }
}