package it.polimi.ingsw.model.singlePlayer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ActionFaithTest {

@Test
    public void testAddFaith() {
        int expectedFaith= DarkFaith.getDarkFaith()+2;
        ActionFaith actionFaith= new ActionFaith();
        actionFaith.AddFaith();

        assertEquals(expectedFaith, DarkFaith.getDarkFaith());
    }
}