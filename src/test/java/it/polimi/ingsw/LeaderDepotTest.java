package it.polimi.ingsw;

import it.polimi.ingsw.model.Resource;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class LeaderDepotTest extends TestCase {
    @Test
    public void testActivateFirstDepot() {
        LeaderDepot testLeaderDepot = new LeaderDepot();
        testLeaderDepot.setType(Resource.COIN);
        Depot depot = new Depot();

        testLeaderDepot.activateDepot(depot);
        Resource returnedDepotType = depot.getLeaderType()[0];

        assertSame(Resource.COIN,returnedDepotType);

    }
}