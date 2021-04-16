package it.polimi.ingsw;

import it.polimi.ingsw.model.CardColor;

public abstract class ActionToken {
    int requires;
    // TODO: requirements
}

class ActionFaith extends ActionToken{
     public void AddFaith(){
       int faith=DarkFaith.getDarkFaith();
       DarkFaith.setDarkFaith(faith+2);
    }
}

class ActionShuffle extends ActionToken{
    public void AddFaith(){
        int faith=DarkFaith.getDarkFaith();
        DarkFaith.setDarkFaith(faith+1);
        ActionPile.ShufflePile();
    }
}

class ActionRemove extends ActionToken{
    private CardColor cardColor;

    public void RemoveDevCard(){
        //TODO: revome 2 devCard with same cardColor
    }
}