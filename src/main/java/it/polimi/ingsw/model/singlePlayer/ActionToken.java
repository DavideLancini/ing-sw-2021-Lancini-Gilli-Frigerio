package it.polimi.ingsw.model.singlePlayer;

import it.polimi.ingsw.model.CardColor;

public abstract class ActionToken {
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