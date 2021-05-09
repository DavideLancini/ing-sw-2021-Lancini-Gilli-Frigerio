package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class TestDepot {
    Depot depot = new Depot();
    Resource resource = Resource.COIN;


    @Test
    public void testDeposit(){

        try {
            depot.deposit(resource, 0);
        }
        catch (Exception e) {}

        assertSame(resource, depot.getResource(0));


    }

    @Test
    public void testExtract(){
        try {
            depot.deposit(resource, 0);
        }
        catch (Exception e) {}

        Resource extracted = null;
        try {extracted = depot.extract(0);}
        catch (Exception e){}

        assertSame(resource,extracted);
        assertSame(null,depot.getResource(0));

    }
}
