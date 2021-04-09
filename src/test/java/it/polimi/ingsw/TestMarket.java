package it.polimi.ingsw;

import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Market;
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
    //TODO add test for takeResources
}