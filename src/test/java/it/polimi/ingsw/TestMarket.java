package it.polimi.ingsw;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMarket
{
    @Test
    public void trySetSideMarble() {
        Marble testSide = Marble.WHITE;
        Market.setSideMarble(testSide);
        Marble returnedSide = Market.getSideMarble();

        assertSame(testSide, returnedSide);
    }
    @Test
    public void trySetSideMarbleToEMPTY()
    {
        Marble testSide = Marble.EMPTY;
        try {
            Market.setSideMarble(testSide);
        }
        catch (Exception ignored){
        }
    }
}
