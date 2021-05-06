package it.polimi.ingsw.model.singlePlayer;

import java.util.Collections;
import java.util.Stack;

/**
 * class ActionPile
 * @author Gruppo 12
 */
public class ActionPile {
    private static Stack<ActionToken> pile;

    /**
     * ShufflePile
     * shuffle pile of ActionToken
     */
    public static void ShufflePile(){
        Collections.shuffle(pile);
    }

}
