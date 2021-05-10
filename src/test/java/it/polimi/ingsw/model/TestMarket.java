package it.polimi.ingsw.model;



import org.junit.Before;
import org.junit.Test;

import static it.polimi.ingsw.model.Market.*;
import static org.junit.Assert.*;

public class TestMarket {

    Marble[][] testMarketBoard = {
            {Marble.WHITE, Marble.BLUE, Marble.GRAY, Marble.YELLOW},
            {Marble.PURPLE, Marble.RED, Marble.WHITE, Marble.BLUE},
            {Marble.GRAY, Marble.YELLOW, Marble.PURPLE, Marble.RED}
    };
    Marble testSideMarble = Marble.RED;

    @Before
    @Test
    public void trySetSideMarbleWithCorrectValue() {

        setSideMarble(testSideMarble);
        Marble returnedSideMarble = Market.getSideMarble();

        assertSame(testSideMarble, returnedSideMarble);
    }

    @Before
    @Test
    public void trySetMarketBoardWithCorrectValues() {
        setMarketBoard(testMarketBoard);
        Marble[][] returnedMarketBoard = Market.getMarketBoard();

        assertSame(testMarketBoard, returnedMarketBoard);
    }

    @Test
    public void tryTakeResources() throws Exception {

        Marble[] returnedRow;
        Marble[] expectedRow = new Marble[]{Marble.WHITE, Marble.BLUE, Marble.GRAY, Marble.YELLOW};
        Marble[] returnedCol;
        Marble[] expectedCol = new Marble[]{Marble.YELLOW, Marble.WHITE, Marble.PURPLE};
        marketView();
        returnedRow = Market.takeResources(true, 1);
        assertArrayEquals(expectedRow, returnedRow);
        assertSame(Marble.WHITE, Market.getSideMarble());
        marketView();
        returnedCol = Market.takeResources(false, 3);
        assertArrayEquals(expectedCol, returnedCol);
        marketView();
        assertSame(Marble.YELLOW, Market.getSideMarble());

    }


}