package it.polimi.ingsw.model;

import java.util.Stack;

/**
 * Class DevCardDeck
 * @author Group 12
 */
public class DevCardDeck {

    private final Stack<DevCard> deck;

    /**
     * constructor
     * @param cards 4 development cards
     */
    public DevCardDeck (DevCard[] cards){
        this.deck = new Stack<>();
        for(DevCard each : cards){
            this.deck.push(each);
        }
    }

    public Stack<DevCard> getDeck() {
        return deck;
    }
    /**
     * draw
     * @return DevCard on top of deck and remove it from deck
     */
     public DevCard draw(){
         return this.deck.pop();
     }

    /**
     * peek
     * @return DevCard on top of deck
     */
     public DevCard peek(){
         return deck.size() == 0 ? null : deck.peek();
     }

}
