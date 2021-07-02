package it.polimi.ingsw.psp12.model;



import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMarket {
    final Market market = new Market();
    final Marble[][] testMarketBoard = {
            {Marble.WHITE, Marble.BLUE, Marble.GRAY, Marble.YELLOW},
            {Marble.PURPLE, Marble.RED, Marble.WHITE, Marble.BLUE},
            {Marble.GRAY, Marble.YELLOW, Marble.PURPLE, Marble.RED}
    };
    final Marble testSideMarble = Marble.RED;

    @Before
    @Test
    public void trySetSideMarbleWithCorrectValue() {

        market.setSideMarble(testSideMarble);
        Marble returnedSideMarble = market.getSideMarble();

        assertSame(testSideMarble, returnedSideMarble);
    }

    @Before
    @Test
    public void trySetMarketBoardWithCorrectValues() {
        market.setMarketBoard(testMarketBoard);
        Marble[][] returnedMarketBoard = market.getMarketBoard();

        assertSame(testMarketBoard, returnedMarketBoard);
    }

    @Test
    public void tryTakeResources() throws Exception {

        Marble[] returnedRow;
        Marble[] expectedRow = new Marble[]{Marble.WHITE, Marble.BLUE, Marble.GRAY, Marble.YELLOW};
        Marble[] returnedCol;
        Marble[] expectedCol = new Marble[]{Marble.YELLOW, Marble.WHITE, Marble.PURPLE};
        System.out.println(market.view());
        returnedRow = market.takeResources(true, 1);
        assertArrayEquals(expectedRow, returnedRow);
        assertSame(Marble.WHITE, market.getSideMarble());
        System.out.println(market.view());
        returnedCol = market.takeResources(false, 3);
        assertArrayEquals(expectedCol, returnedCol);
        System.out.println(market.view());
        assertSame(Marble.YELLOW, market.getSideMarble());

    }


}