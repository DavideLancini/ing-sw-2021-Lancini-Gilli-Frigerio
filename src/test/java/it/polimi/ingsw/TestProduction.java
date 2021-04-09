package it.polimi.ingsw;

import it.polimi.ingsw.model.Resource;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestProduction {
    @Test
    public void tryProduceWithCorrectInput(){
        Resource[] in = {Resource.SHIELD, Resource.STONE};
        Resource[] out = {Resource.SERVANT, Resource.SERVANT, Resource.SERVANT};

        Production testProduction = new Production(in, out);
        Resource[] returnedOutput = {};
        try {
            returnedOutput = testProduction.produce(in);
        }
        catch(Exception e){}
        assertSame(out, returnedOutput);
    }
}
