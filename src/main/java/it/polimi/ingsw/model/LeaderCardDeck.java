package it.polimi.ingsw.model;

import java.util.Arrays;

public class LeaderCardDeck {

public LeaderCard[] deck = new LeaderCard[16];
private LeaderCard[] given = new LeaderCard[4];
    /*
    +DrawTwo(Given): void
    +SetGiven(): void
     */

    public void setDeck(LeaderCard[] deck) {
        this.deck = deck;
    }
    public LeaderCard[] getDeck(){
        return deck;
    }

    public void setGiven(LeaderCard[] given) {
        this.given = given;
    }
    public LeaderCard[] getGiven() {
        return given;
    }

    public void DrawTwo(){
        //Show 4 given
        //User select 2 from given
        //set playerBoard.leaderCards
    }
}