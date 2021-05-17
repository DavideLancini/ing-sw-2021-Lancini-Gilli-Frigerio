package it.polimi.ingsw.model;

import org.junit.Test;
import static org.junit.Assert.*;

public  class LeaderTransformTest {

    @Test
    public void whiteTo() throws Exception {
        LeaderCard leaderCard = new LeaderTransform(12,Resource.COIN, new Resource[]{Resource.STONE, Resource.STONE});
        leaderCard.setType(Resource.COIN);
        Resource output = leaderCard.WhiteTo(Marble.WHITE);
        Resource exOutput = Resource.COIN;

        assertSame(exOutput, output);
    }
}