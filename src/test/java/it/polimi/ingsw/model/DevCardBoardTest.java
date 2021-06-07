package it.polimi.ingsw.model;

import org.junit.Test;

public class DevCardBoardTest {

    Resource[] r1= {Resource.SHIELD,Resource.SERVANT,Resource.SHIELD,Resource.SHIELD};
    Resource[] r2= {Resource.STONE,Resource.STONE};
    Resource[] r3= {Resource.SHIELD,Resource.FAITH,Resource.FAITH};
    Resource[] r4= {Resource.COIN,Resource.COIN};

    Production p1= new Production(r2,r3);
    Production p2= new Production(r1,r4);
    Production p3= new Production(r4,r2);

    DevCard d1= new DevCard(Level.TWO,CardColor.BLUE,9,r1,p1);
    DevCard d2= new DevCard(Level.ONE,CardColor.GREEN,9,r3,p2);
    DevCard d3= new DevCard(Level.THREE,CardColor.YELLOW,9,r4,p3);

    DevCardDeck[][] board={
            {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d3,d2,d1})},
            {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d2,d1,d3})},
            {new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d2,d1,d3}), new DevCardDeck(new DevCard[]{d3,d2,d1})},
            {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d1,d3}), new DevCardDeck(new DevCard[]{d3,d2,d1})}
    };
    DevCardBoard dcb = new DevCardBoard(board);
    @Test
    public void DevCardBordViewTest() {
        System.out.println(dcb.topView());
    }
}