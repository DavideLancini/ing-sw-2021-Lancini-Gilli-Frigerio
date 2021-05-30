package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DevCardTest {
    Resource[] r1= {Resource.SHIELD,Resource.SERVANT,Resource.SHIELD,Resource.SHIELD};
    Resource[] r2= {Resource.STONE,Resource.STONE};
    Resource[] r3= {Resource.SHIELD,Resource.FAITH,Resource.FAITH};
    Production p1= new Production(r2,r3);
    DevCard d1= new DevCard(Level.TWO,CardColor.BLUE,9,r1,p1);
    @Test
    public void getProduction() {
        assertSame(p1,d1.getProduction());
    }

    @Test
    public void devCardView() {;
        assertEquals(d1.toString(),d1.devCardView());
    }
}