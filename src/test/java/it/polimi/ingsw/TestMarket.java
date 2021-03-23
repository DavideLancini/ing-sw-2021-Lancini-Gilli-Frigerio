package it.polimi.ingsw;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMarket
{
    @Test
    public void trySetMarbleWithCorrectValue() {
        Market market = new Market();
        Marble testSide = Marble.WHITE;
        market.setSideMarble(testSide);
        Marble returnedSide = market.getSideMarble();

        assertSame(testSide, returnedSide);
    }
    @Test
    public void trySetMarbleWithWrongValue()
    {
        Market market = new Market();
        Marble testSide = Marble.EMPTY;
        try {
            market.setSideMarble(testSide);
        }
        catch (Exception ignored){
        }
    }
}
