package it.polimi.ingsw.model;

import java.util.Arrays;
/**
 * Class LeaderCardDeck
 * @author Gruppo 12
 */
public class LeaderCardDeck {

public LeaderCard[] deck = new LeaderCard[16];
private LeaderCard[] given = new LeaderCard[4];

    /**
     * setDeck
     * @param deck initialized leaderCards for the deck
     */
    public void setDeck(LeaderCard[] deck) {
        this.deck = deck;
    }

    /**
     * getDeck
     * @return all LeaderCardDeck
     */
    public LeaderCard[] getDeck(){
        return deck;
    }

    /**
     * setGiven
     * @param given 4 leaderCard from deck
     */
    public void setGiven(LeaderCard[] given) {
        this.given = given;
    }

    /**
     * getGiven
     * @return 4 leaderCards
     */
    public LeaderCard[] getGiven() {
        return given;
    }

    /**
     * DrawTwo
     * select two cards to place in playerBoard
     */
    public void DrawTwo(){
        //Show 4 given
        //User select 2 from given
        //set playerBoard.leaderCards
    }
}