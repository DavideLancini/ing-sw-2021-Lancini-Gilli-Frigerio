package it.polimi.ingsw;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMarket
{
    @Test
    public void trySetSideMarble() {
        Market testMarket = new Market();
        Marble testSide = Marble.WHITE;
        testMarket.setSideMarble(testSide);
        Marble returnedSide = testMarket.getSideMarble();

        assertSame(testSide, returnedSide);
    }
    @Test
    public void trySetSideMarbleToEMPTY()
    {
        Market testMarket = new Market();
        Marble testSide = Marble.EMPTY;
        try {
            testMarket.setSideMarble(testSide);
        }
        catch (Exception ignored){
        }
    }
}
