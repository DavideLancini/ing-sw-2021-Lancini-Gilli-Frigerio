package it.polimi.ingsw.model.singlePlayer;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActionPileTest {

    @Test
    public void getFirst() {
        ActionPile actionPile=new ActionPile();
        ActionToken actionToken1=actionPile.getFirst();
        ActionToken actionToken2=actionPile.getFirst();
        assertNotSame(actionToken1,actionToken2);
    }
}