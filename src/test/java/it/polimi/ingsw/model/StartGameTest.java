package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class StartGameTest {
    PlayerBoard[] playerBoards;
@Test
    public void testStarGame() throws FileNotFoundException {
    new StartGame(/*players*/);
    DevCardBoard.topView(DevCardBoard.getTop(DevCardBoard.getBoard()));
    for (LeaderCard leaderCard : LeaderCardDeck.deck){
        leaderCard.leaderCardView();
    }
    Market.marketView();
    }

}