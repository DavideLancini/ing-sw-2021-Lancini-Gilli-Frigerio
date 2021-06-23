package it.polimi.ingsw.view;

import com.google.gson.Gson;
import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestViewHelper {
    @Test
    public void TestS2SDevCard() {
        String first = new DevCard(Level.THREE, CardColor.GREEN, 12, new Resource[]{Resource.STONE, Resource.SHIELD},
                new Production(new Resource[]{Resource.STONE, Resource.STONE}, new Resource[]{Resource.SHIELD}, 1),"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png").devCardView();
        String second = new DevCard(Level.ONE, CardColor.YELLOW, 3, new Resource[]{Resource.COIN, Resource.COIN, Resource.SERVANT},
                new Production(new Resource[]{Resource.SERVANT, Resource.STONE}, new Resource[]{Resource.COIN}),"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png").devCardView();

        System.out.println(first);
        System.out.println(second + "\n");
        System.out.println(ViewHelper.displayS2S(first, second));

    }

    @Test
    public void TestS2SLeader() {
        String first = new LeaderDepot(1, Resource.SHIELD, Resource.COIN,"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png").view();
        String second = new LeaderSale(3, Resource.COIN, new CardColor[]{CardColor.PURPLE, CardColor.YELLOW},"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png").view();
        String third = new LeaderTransform(3, Resource.SERVANT, new CardColor[]{CardColor.BLUE},"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png").view();
        String fourth = new LeaderProduction(7, Resource.STONE, CardColor.GREEN, new Production(new Resource[]{}, new Resource[]{}),"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png").view();

        String[] array = new String[]{first, second, third, fourth};
        List<String> aarray = Arrays.asList(array);
        Collections.shuffle(aarray);
        array = aarray.toArray(String[]::new);

        System.out.println(first);
        System.out.println(second + "\n");
        System.out.println(third + "\n");
        System.out.println(fourth + "\n");


        System.out.println(ViewHelper.displayS2S(array));

    }

    @Test
    public void TestDevBoard() throws FileNotFoundException {
        DevCardBoard board = new DevCardBoard(new Gson().fromJson(new FileReader("src/main/resources/DevCardBOARD.json"),DevCardDeck[][].class));
        System.out.println(board.topView());


    }
}
