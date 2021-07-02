package it.polimi.ingsw.psp12.controller;


import com.google.gson.Gson;
import it.polimi.ingsw.psp12.model.*;
import it.polimi.ingsw.psp12.network.DisconnectedException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class TestController {
    final LeaderCard[] leaders = new LeaderCard[]{new LeaderDepot(1, Resource.COIN, Resource.SHIELD,"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png"),
            new LeaderSale(2, Resource.SHIELD, new CardColor[]{CardColor.PURPLE},"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png")};
    final PlayerBoard pb = new PlayerBoard(leaders);
    DevCardDeck[][] board;

    {
        try {
            board = new Gson().fromJson(new FileReader("src/main/resources/DevCardBOARD.json"),DevCardDeck[][].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    final DevCardBoard dcb = new DevCardBoard(board);
    final Market market = new Market();
    final Controller controller = new Controller(pb, dcb, market);

    @Test
    public void TestSellLeader() throws DisconnectedException {
        controller.sellLeader(0);
        assertSame(null, pb.getLeaderCard(0));
        assertSame(1, pb.getFaith());
    }

    @Test
    public void TestActivateLeader() throws DisconnectedException {
        try {
            pb.addDevCard(new DevCard(Level.ONE, CardColor.PURPLE, 1, new Resource[]{Resource.COIN}, new Production(new Resource[]{Resource.SHIELD}, new Resource[]{Resource.COIN}),"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png"), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.activateLeader(1);
        assertTrue(pb.getLeaderCard(1).getIsActive());
    }

    @Test
    public void TestBuyDevCard() throws DisconnectedException {
        DevCard card = dcb.getCard(CardColor.GREEN, Level.ONE);
        pb.getStrongbox().deposit(new ArrayList<>(Arrays.asList(
                Resource.SHIELD, Resource.SHIELD, Resource.SHIELD, Resource.SHIELD, Resource.SHIELD,
                Resource.STONE, Resource.STONE, Resource.STONE, Resource.STONE, Resource.STONE,
                Resource.COIN, Resource.COIN, Resource.COIN, Resource.COIN, Resource.COIN,
                Resource.SERVANT, Resource.SERVANT, Resource.SERVANT, Resource.SERVANT, Resource.SERVANT
        )));
        controller.buyDevCard(Level.ONE, CardColor.GREEN, 1);
        assertSame(card, pb.getDevCard(1));

    }

    @Test
    public void TestProduce() throws DisconnectedException {
        DevCard card = dcb.getCard(CardColor.GREEN, Level.ONE);
        try {
            pb.addDevCard(card, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pb.getStrongbox().deposit(new ArrayList<>(Arrays.asList(
                Resource.SHIELD, Resource.SHIELD, Resource.SHIELD, Resource.SHIELD, Resource.SHIELD,
                Resource.STONE, Resource.STONE, Resource.STONE, Resource.STONE, Resource.STONE,
                Resource.COIN, Resource.COIN, Resource.COIN, Resource.COIN, Resource.COIN,
                Resource.SERVANT, Resource.SERVANT, Resource.SERVANT, Resource.SERVANT, Resource.SERVANT
        )));

        controller.produce(new boolean[]{false, true, false, false, false, false});

       pb.playerBoardView("testPlayer");
    }
}
