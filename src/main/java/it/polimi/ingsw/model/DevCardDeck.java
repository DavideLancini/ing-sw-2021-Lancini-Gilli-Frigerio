package it.polimi.ingsw.model;

import java.util.Stack;
/**
 * Class DevCardDeck
 * @author Gruppo 12
 */
public class DevCardDeck {
     private Stack<DevCard> deck;

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
         return this.deck.peek();
     }
}
