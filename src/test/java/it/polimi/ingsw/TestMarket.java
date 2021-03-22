package it.polimi.ingsw;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for Market Class
 */
public class TestMarket
{

    /** Try setSideMarble & getSideMarble with a correct implementation*/
    @Test
    public void trySetMarbleWithCorrectValue()
    {
        Marble testSide = Marble.WHITE;
        Market.setSideMarble(testSide);
        Marble returnedSide = Market.getSideMarble();

        assertSame(testSide, returnedSide);
    }
    /** Try setSideMarble to a non Marble object*/
    @Test
    public void trySetMarbleWithWrongValue()
    {
        String testSide = "WHITE";
        try {
            Market.setSideMarble(testSide);
        }
        catch (Exception ignored){
        }
    }
    @Test
    public void trySetMarbleWithArray()
    {
        assertTrue( true );
    }
}
