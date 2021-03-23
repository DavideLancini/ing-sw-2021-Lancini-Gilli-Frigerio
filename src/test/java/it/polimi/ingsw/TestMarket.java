package it.polimi.ingsw;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMarket
{
    @Test
    public void trySetMarbleWithCorrectValue() {
        Market market = new Market();
        Marble testSideMarble = Marble.WHITE;
        market.setSideMarble(testSideMarble);
        Marble returnedSideMarble = market.getSideMarble();

        assertSame(testSideMarble, returnedSideMarble);
    }
    @Test
    public void trySetMarbleWithWrongValue()
    {
        Market market = new Market();
        Marble testSideMarble = Marble.EMPTY;
        try {
            market.setSideMarble(testSideMarble);
        }
        catch (Exception ignored){
        }
    }
}
