package it.polimi.ingsw;

import it.polimi.ingsw.model.Resource;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;

public class TestStrongbox {
    @Test
    public void testHas(){
        Strongbox strongbox = new Strongbox();
        ArrayList<Resource> input = new ArrayList(Arrays.asList(Resource.COIN, Resource.STONE, Resource.COIN, Resource.STONE, Resource.SHIELD));
        strongbox.deposit(input);
        ArrayList<Resource> check = new ArrayList(Arrays.asList(Resource.COIN, Resource.STONE, Resource.STONE));


        boolean returnedbool = strongbox.contains(check);
        assertSame(true, returnedbool);
    }
}
