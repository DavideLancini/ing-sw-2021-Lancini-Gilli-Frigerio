package it.polimi.ingsw.model.singlePlayer;

import it.polimi.ingsw.model.*;

/**
 * class ActionToken
 * @author Gruppo 12
 */
public abstract class ActionToken {
    protected ActionType actionType;

    public boolean resolve(DevCardBoard devCardBoard){
        return false;
    }

    public ActionType getType() {
        return actionType;
    }

    public abstract String view();
}
/**
 * class ActionFaith extends ActionToken
 * @author Gruppo 12
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
 * @author Gruppo 12
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
 * @author Gruppo 12
 */
class ActionRemove extends ActionToken{
    private final CardColor cardColor;
    public ActionRemove(CardColor cardColor){
        this.actionType=ActionType.RemoveDevCard;
        this.cardColor=cardColor;
    }

    /**
     * Removes two cards of this.cardColor until devCardDecks of this.cardColor are empty
     * @param devCardBoard current board in the game
     * @return endGame true if all cards have been removed
     */
    @Override
    public boolean resolve(DevCardBoard devCardBoard) {
        int i;
        int k=0;
        switch (cardColor){
            case BLUE:i=0;
            break;
            case YELLOW:i=1;
            break;
            case GREEN:i=2;
            break;
            case PURPLE:i=3;
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + cardColor);
        }
        for (int j=0;j<2;j++) {

            if (devCardBoard.board[i][k].peek().getLevel()!= Level.EMPTY)
                devCardBoard.board[i][k].draw();
            else {
                if(k!=2){
                k++;
                j--;
                }
                else return true;
            }
        }

        return false;
    }

    @Override
    public String view() {
        return "┃    -2"+this.cardColor+"     ┃\n";
    }
}