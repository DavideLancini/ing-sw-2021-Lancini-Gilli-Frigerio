package it.polimi.ingsw.model;



import org.junit.Before;
import org.junit.Test;

import static it.polimi.ingsw.model.Market.*;
import static org.junit.Assert.*;

public class TestMarket {
    Market market = new Market();
    Marble[][] testMarketBoard = {
            {Marble.WHITE, Marble.BLUE, Marble.GRAY, Marble.YELLOW},
            {Marble.PURPLE, Marble.RED, Marble.WHITE, Marble.BLUE},
            {Marble.GRAY, Marble.YELLOW, Marble.PURPLE, Marble.RED}
    };
    Marble testSideMarble = Marble.RED;

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
        market.marketView();
        returnedRow = market.takeResources(true, 1);
        assertArrayEquals(expectedRow, returnedRow);
        assertSame(Marble.WHITE, market.getSideMarble());
        market.marketView();
        returnedCol = market.takeResources(false, 3);
        assertArrayEquals(expectedCol, returnedCol);
        market.marketView();
        assertSame(Marble.YELLOW, market.getSideMarble());

    }


}