package it.polimi.ingsw;

import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Market;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMarket {
    Market testMarket = new Market();
    @Before
    @Test
    public void trySetSideMarbleWithCorrectValue() {
        Marble testSideMarble = Marble.WHITE;
        testMarket.setSideMarble(testSideMarble);
        Marble returnedSideMarble = testMarket.getSideMarble();

        assertSame(testSideMarble, returnedSideMarble);
    }
    @Before
    @Test
    public void trySetMarketBoardWithCorrectValues() {

        Marble[][] testMarketBoard = {
                {Marble.WHITE, Marble.BLUE, Marble.GRAY, Marble.YELLOW},
                {Marble.PURPLE, Marble.RED, Marble.WHITE, Marble.BLUE},
                {Marble.GRAY, Marble.YELLOW, Marble.PURPLE, Marble.RED}
        };
        testMarket.setMarketBoard(testMarketBoard);
        Marble[][] returnedMarketBoard = testMarket.getMarketBoard();

        assertSame(testMarketBoard, returnedMarketBoard);
    }

    @Test
    public void tryTakeResources() throws Exception {

        Marble[] returnedRow;
        Marble[] expectedRow=new Marble[]{Marble.WHITE, Marble.BLUE, Marble.GRAY, Marble.YELLOW};
        Marble[] returnedCol;
        Marble[] expectedCol=new Marble[]{Marble.YELLOW,Marble.WHITE,Marble.PURPLE};

        returnedRow=testMarket.takeResources(true,1);
        assertArrayEquals(expectedRow,returnedRow);
        assertEquals(Marble.WHITE,testMarket.getSideMarble());

        returnedCol=testMarket.takeResources(false,3);
        assertArrayEquals(expectedCol,returnedCol);
        assertEquals(Marble.YELLOW,testMarket.getSideMarble());

    }
}