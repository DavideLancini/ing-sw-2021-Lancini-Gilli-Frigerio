package it.polimi.ingsw.model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertSame;

public class TestStrongbox {
    @Test
    public void testContains(){
        Strongbox strongbox = new Strongbox();
        ArrayList<Resource> input = new ArrayList(Arrays.asList(Resource.COIN, Resource.STONE, Resource.COIN, Resource.STONE, Resource.SHIELD));
        strongbox.deposit(input);
        strongbox.StrongboxView();
        ArrayList<Resource> check = new ArrayList(Arrays.asList(Resource.COIN, Resource.STONE, Resource.STONE));


        boolean returnedbool = strongbox.contains(check);
        assertSame(true, returnedbool);

        Strongbox strongbox2 = new Strongbox();

        ArrayList<Resource> input2 = new ArrayList(Arrays.asList(Resource.COIN, Resource.STONE, Resource.COIN, Resource.SHIELD,Resource.SERVANT));
        strongbox2.deposit(input2);
        strongbox2.StrongboxView();
        ArrayList<Resource> check2 = new ArrayList(Arrays.asList(Resource.COIN, Resource.STONE, Resource.STONE));


        boolean returnedbool2 = strongbox2.contains(check2);
        assertSame(false, returnedbool2);

    }
}
