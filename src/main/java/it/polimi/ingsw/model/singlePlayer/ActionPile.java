package it.polimi.ingsw.model.singlePlayer;

import it.polimi.ingsw.model.CardColor;

import java.util.*;

/**
 * class ActionPile
 * @author Group 12
 */
public class ActionPile {
    private Stack<ActionToken> pile;

    /**
     * Constructor
     */
    public ActionPile(){
       setupActionPile();
    }

    /**
     * add correct elements to pile and shuffles it
     */
    public void setupActionPile() {
        this.pile= new Stack<>();
        this.pile.addAll(Arrays.asList(new ActionRemove(CardColor.BLUE), new ActionRemove(CardColor.GREEN), new ActionRemove(CardColor.YELLOW), new ActionRemove(CardColor.PURPLE)));
        this.pile.add(new ActionShuffle());
        this.pile.addAll(Arrays.asList(new ActionFaith(),new ActionFaith()));
        Collections.shuffle(this.pile);
    }

    /**
     *
     * @return first element of pile
     */
    public ActionToken getFirst() {
        return this.pile.pop();
    }
}
