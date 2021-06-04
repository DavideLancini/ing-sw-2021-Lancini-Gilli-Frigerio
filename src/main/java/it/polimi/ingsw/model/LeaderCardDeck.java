package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Class LeaderCardDeck
 * @author Gruppo 12
 */
public class LeaderCardDeck {

private Stack<LeaderCard> deck;
    /**
     * LeaderCardDeck constructor
     * @param leaderCards initialized leaderCards for the deck
     */
    public LeaderCardDeck(LeaderCard[] leaderCards) {
        ArrayList<LeaderCard> leaders = new ArrayList<>(Arrays.asList(leaderCards));
        Collections.shuffle(leaders);
        this.deck = new Stack<LeaderCard>();
        this.deck.addAll(leaders);
    }

    public LeaderCard[] draw4(){
        return new LeaderCard[]{deck.pop(), deck.pop(), deck.pop(), deck.pop()};
    }

}