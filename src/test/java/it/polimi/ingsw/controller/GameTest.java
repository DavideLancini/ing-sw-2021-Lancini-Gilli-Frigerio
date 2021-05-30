package it.polimi.ingsw.controller;
import it.polimi.ingsw.model.DevCardBoard;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.PlayerBoard;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class GameTest{
    PlayerBoard p1= new PlayerBoard();
    PlayerBoard[] pbs= new PlayerBoard[1];
    @Test
    public void Game() throws FileNotFoundException {
        Game game= new Game(pbs);
        game.devCardBoard.topView(DevCardBoard.getTop(DevCardBoard.getBoard()));
        for (LeaderCard leaderCard : game.leaderCardDeck.deck){
            leaderCard.leaderCardView();
        }
        game.market.marketView();
        assertSame(game.i,1);
    }

}