package it.polimi.ingsw.model;

import org.junit.Test;
import static org.junit.Assert.*;

public  class LeaderTransformTest {

    @Test
    public void whiteTo() throws Exception {
        LeaderTransform leaderCard = new LeaderTransform();
        leaderCard.setType(Resource.COIN);
        Resource output = leaderCard.WhiteTo(Marble.WHITE);
        Resource exOutput = Resource.COIN;

        assertSame(exOutput, output);
    }
}