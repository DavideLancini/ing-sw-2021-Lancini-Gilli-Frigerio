package it.polimi.ingsw.psp12.model;

import org.junit.Test;

public class DevCardBoardTest {

    final Resource[] r1= {Resource.SHIELD,Resource.SERVANT,Resource.SHIELD,Resource.SHIELD};
    final Resource[] r2= {Resource.STONE,Resource.STONE};
    final Resource[] r3= {Resource.SHIELD,Resource.FAITH,Resource.FAITH};
    final Resource[] r4= {Resource.COIN,Resource.COIN};

    final Production p1= new Production(r2,r3);
    final Production p2= new Production(r1,r4);
    final Production p3= new Production(r4,r2);

    final DevCard d1= new DevCard(Level.TWO,CardColor.BLUE,9,r1,p1,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png");
    final DevCard d2= new DevCard(Level.ONE,CardColor.GREEN,9,r3,p2,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-2-1.png");
    final DevCard d3= new DevCard(Level.THREE,CardColor.YELLOW,9,r4,p3,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-3-1.png");

    final DevCardDeck[][] board={
            {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d3,d2,d1})},
            {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d2,d1,d3})},
            {new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d2,d1,d3}), new DevCardDeck(new DevCard[]{d3,d2,d1})},
            {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d1,d3}), new DevCardDeck(new DevCard[]{d3,d2,d1})}
    };
    final DevCardBoard dcb = new DevCardBoard(board);
    @Test
    public void DevCardBordViewTest() {
        System.out.println(dcb.topView());
    }
}