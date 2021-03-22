package it.polimi.ingsw;

import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for Market Class
 */
public class TestMarket
{
    @Test
    public void trySetMarbleWithCorrectValue()
    {
        Marble testSide = Marble.WHITE;
        Market.setSideMarble(testSide);
        Marble returnedSide = Market.getSideMarble();

        assertSame(testSide, returnedSide);
    }
    @Test
    public void trySetMarbleWithWrongValue()
    {
        assertTrue( true );
    }
    @Test
    public void trySetMarbleWithArray()
    {
        assertTrue( true );
    }
}
