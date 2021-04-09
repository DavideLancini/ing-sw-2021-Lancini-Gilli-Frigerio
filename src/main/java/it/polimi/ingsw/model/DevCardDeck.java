package it.polimi.ingsw.model;

import java.util.Stack;

public class DevCardDeck {
     private Stack<DevCard> deck;

     public DevCard draw(){
         return this.deck.pop();
     }

     public DevCard peek(){
         return this.deck.peek();
     }
}
