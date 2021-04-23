package it.polimi.ingsw.model;

import it.polimi.ingsw.Depot;

public class PlayerBoard {

    private int faithTrack = 0;
    private Strongbox strongbox;
    private LeaderCard leaderCards[];
    private Depot depot;
    private DevCard devCards[][];

    // Return new faith for easy access
    public int addFaith(int newFaith){
        this.faithTrack += newFaith;
        return this.faithTrack;
    }

    public int getFaith(){
        return this.faithTrack;
    }

    public Strongbox getStrongbox(){
        return this.strongbox;
    }


    //TODO Exceptions and handling
    public void addDevCard(DevCard newDevCard, int position) throws Exception{
        Level level = newDevCard.getLevel();
        DevCard[] deck = this.devCards[position];
        //Check new DevCard is whitin rules or throws an exceptions
        switch(level){
            case ONE:
                if (deck[0] == null) {deck[0] = newDevCard; break;}
                else throw new Exception("");
            case TWO:
                if (deck[1] == null && deck[0].getLevel() == Level.ONE) {deck[1] = newDevCard; break;}
                else throw new Exception("");
            case THREE:
                if (deck[2] == null && deck[1].getLevel() == Level.TWO) {deck[2] = newDevCard; break;}
                else throw new Exception("");
        }

    }
}
