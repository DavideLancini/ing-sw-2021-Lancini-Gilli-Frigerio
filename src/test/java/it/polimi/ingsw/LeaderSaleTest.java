package it.polimi.ingsw;

import it.polimi.ingsw.model.Resource;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class LeaderSaleTest extends TestCase {
    @Test
    public void testDownPrice() {
        LeaderSale testleaderSale=new LeaderSale();
        testleaderSale.setType(Resource.COIN);
        Resource[] cost ={Resource.STONE,Resource.COIN,Resource.COIN};

        testleaderSale.DownPrice(cost);

        assertSame(Resource.STONE,cost[0]);
        assertSame(null,cost[1]);
        assertSame(Resource.COIN,cost[2]);
    }
}