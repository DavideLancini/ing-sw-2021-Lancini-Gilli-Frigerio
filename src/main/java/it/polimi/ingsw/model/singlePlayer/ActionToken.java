package it.polimi.ingsw.model.singlePlayer;

import it.polimi.ingsw.model.*;

/**
 * class ActionToken
 * @author Group 12
 */
public abstract class ActionToken {
    protected ActionType actionType;

    public ActionType getType() {
        return actionType;
    }

    public abstract String view();
}
/**
 * class ActionFaith extends ActionToken
 * @author Group 12
 */
class ActionFaith extends ActionToken{
    public ActionFaith(){
        this.actionType=ActionType.AddFaith;
    }

    @Override
    public String view() {
        return "┃     +2"+ Resource.FAITH+"     ┃\n";
    }
}
/**
 * class ActionShuffle extends ActionToken
 * @author Group 12
 */
class ActionShuffle extends ActionToken{
    public ActionShuffle(){
        this.actionType=ActionType.ShufflePile;
    }

    @Override
    public String view() {
        return "┃+1"+ Resource.FAITH+" + shuffle    ┃\n";
    }
}
/**
 * class ActionRemove extends ActionToken
 * @author Group 12
 */
class ActionRemove extends ActionToken{
    private final CardColor cardColor;

    /**
     * removes 2 development card of lowest level and correct color from the game
     * @param cardColor color of card to be removed
     */
    public ActionRemove(CardColor cardColor){
        this.actionType=ActionType.RemoveDevCard;
        this.cardColor=cardColor;
    }

    public CardColor getCardColor(){return this.cardColor;}


    @Override
    public String view() {
        return "┃    -2"+this.cardColor+"     ┃\n";
    }
}