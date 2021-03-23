package it.polimi.ingsw;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMarket
{
    @Test
    public void trySetSideMarbleWithCorrectValue() {
        Market testMarket = new Market();
        Marble testSideMarble = Marble.WHITE;
        testMarket.setSideMarble(testSideMarble);
        Marble returnedSideMarble = testMarket.getSideMarble();

        assertSame(testSideMarble, returnedSideMarble);
    }
    @Test
    public void trySetSideMarbletoEMPTY()
    {
        Market testMarket = new Market();
        Marble testSideMarble = Marble.EMPTY;
        try {
            testMarket.setSideMarble(testSideMarble);
        }
        catch (Exception ignored){
        }
    }
    // the actual setMarketBoard is not complete yet
    @Test
    public void trySetMarketBoardWithCorrectValues() {
        Market testMarket = new Market();
        Marble[][] testMarketBoard = {
                {Marble.WHITE, Marble.BLUE, Marble.GRAY, Marble.YELLOW},
                {Marble.PURPLE, Marble.RED, Marble.WHITE, Marble.BLUE},
                {Marble.GRAY, Marble.YELLOW, Marble.PURPLE, Marble.RED}
        };
        testMarket.setMarketBoard(testMarketBoard);
        Marble[][] returnedMarketBoard = testMarket.getMarketBoard();

        assertSame(testMarketBoard, returnedMarketBoard);
    }
}
