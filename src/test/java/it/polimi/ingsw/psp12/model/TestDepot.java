package it.polimi.ingsw.psp12.model;

import org.junit.Test;

import static org.junit.Assert.assertSame;


public class TestDepot {
    final Depot depot = new Depot();
    final Resource resource = Resource.COIN;


    @Test
    public void testDeposit(){



        try {
            depot.deposit(resource, 0);
        }
        catch (Exception ignored) {}

        assertSame(resource, depot.getResource(0));


    }

    @Test
    public void testExtract(){
        try {
            depot.deposit(resource, 0);
        }
        catch (Exception ignored) {}

        Resource extracted = Resource.EMPTY;
        try {extracted = depot.extract(0);}
        catch (Exception ignored){}

        assertSame(resource,extracted);
        assertSame(Resource.EMPTY,depot.getResource(0));

    }
    @Test
    public void testDepotView(){
        Resource[] contents= {Resource.COIN, Resource.STONE, Resource.SHIELD, Resource.SHIELD, Resource.EMPTY, Resource.SERVANT, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY};
        depot.setContents(contents);
        System.out.println(depot.depotView());
    }
}
