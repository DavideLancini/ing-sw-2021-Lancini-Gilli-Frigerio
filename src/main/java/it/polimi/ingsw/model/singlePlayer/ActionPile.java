package it.polimi.ingsw.model.singlePlayer;

import java.util.Collections;
import java.util.Stack;

public class ActionPile {
    private static Stack<ActionToken> pile;

    public static void ShufflePile(){
        Collections.shuffle(pile);
    }

}
