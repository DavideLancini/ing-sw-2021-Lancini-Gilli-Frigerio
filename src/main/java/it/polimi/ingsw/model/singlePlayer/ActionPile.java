package it.polimi.ingsw.model.singlePlayer;

import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.network.Message;

import java.util.*;

/**
 * class ActionPile
 * @author Gruppo 12
 */
public class ActionPile {
    private  List <ActionToken> pile;

    public ActionPile(){
       setupActionPile();
       shufflePile();
    }

    private void setupActionPile() {
        pile= new ArrayList<>();
        pile.addAll(Arrays.asList(new ActionRemove(CardColor.BLUE), new ActionRemove(CardColor.GREEN), new ActionRemove(CardColor.YELLOW), new ActionRemove(CardColor.PURPLE)));
        pile.add(new ActionShuffle());
        pile.addAll(Arrays.asList(new ActionFaith(),new ActionFaith()));
    }

    /**
     * ShufflePile
     * shuffle pile of ActionToken
     */
    public void shufflePile(){
        Collections.shuffle(pile);
    }

    public ActionToken getFirst() {
        ActionToken first=pile.remove(0);
        pile.add(first);
        return first;
    }
}
