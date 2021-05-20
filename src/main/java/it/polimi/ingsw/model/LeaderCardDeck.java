package it.polimi.ingsw.model;

import java.util.Arrays;
import java.util.Collections;

/**
 * Class LeaderCardDeck
 * @author Gruppo 12
 */
public class LeaderCardDeck {

public  static  LeaderCard[] deck = new LeaderCard[16];
private static LeaderCard[] given = new LeaderCard[4];
private static int k=0;
    /**
     * LeaderCardDeck constructor
     * @param leaderCards initialized leaderCards for the deck
     */
    public LeaderCardDeck(LeaderCard[] leaderCards) {
        Collections.shuffle(Arrays.asList(leaderCards));
        deck = leaderCards;
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
     * 4 leaderCard from deck
     */
    private static void setGiven() {
       int i=0;
        while ( i<4) {
            given[i] = deck[k];
            i++;
            k++;
        }

    }

    /**
     * getGiven
     * @return 4 leaderCards
     */
    private LeaderCard[] getGiven() {
        return given;
    }

    /**
     * DrawTwo
     * select two cards to place in playerBoard
     */
    public static LeaderCard[] DrawTwo(){
        LeaderCard[] chosen = new LeaderCard[2];
        int i=1;
        LeaderCardDeck.setGiven();
        for(LeaderCard show : given){
            System.out.println(i+":");
            i++;
            show.leaderCardView();//Show 4 given
        }
        System.out.println("select first card: ");
        int selected1 = Reader.in.nextInt();
        chosen[0]=given[selected1-1];
        int selected2 =selected1;
        while (selected2==selected1){
        System.out.println("select second card: ");
        selected2 = Reader.in.nextInt();
        }
        chosen[1]=given[selected2-1];
        //set playerBoard.leaderCards
        return chosen;
    }
}