package it.polimi.ingsw.model.singlePlayer;

import it.polimi.ingsw.model.CardColor;

/**
 * class ActionToken
 * @author Gruppo 12
 */
public abstract class ActionToken {
}
/**
 * class ActionFaith extends ActionToken
 * @author Gruppo 12
 */
class ActionFaith extends ActionToken{
    /**
     * AddFaith
     * add 2 to DarkFaith
     */
     public void AddFaith(){
       int faith=DarkFaith.getDarkFaith();
       DarkFaith.setDarkFaith(faith+2);
    }
}
/**
 * class ActionShuffle extends ActionToken
 * @author Gruppo 12
 */
class ActionShuffle extends ActionToken{
    /**
     * AddFaith
     * add 1 to DarkFaith and shuffle ActionPile
     */
    public void AddFaith(){
        int faith=DarkFaith.getDarkFaith();
        DarkFaith.setDarkFaith(faith+1);
        ActionPile.ShufflePile();
    }
}
/**
 * class ActionRemove extends ActionToken
 * @author Gruppo 12
 */
class ActionRemove extends ActionToken{
    private CardColor cardColor;

    /**
     *
     */
    public void RemoveDevCard(){
        //TODO: revome 2 devCard with same cardColor
    }
}