package it.polimi.ingsw.model;

import org.junit.Test;


public class LeaderCardViewTest {
    final Resource[] cost = {Resource.COIN, Resource.STONE};
    final Production production = new Production(cost, cost);
    final LeaderCard lead1=new LeaderTransform(12,Resource.COIN, new CardColor[]{CardColor.PURPLE, CardColor.YELLOW},"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png");
    final LeaderCard lead2=new LeaderProduction(12,Resource.COIN, CardColor.BLUE, production,"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png");
    final LeaderCard lead3=new LeaderSale(12,Resource.COIN, new CardColor[]{CardColor.BLUE, CardColor.BLUE, CardColor.GREEN},"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png");
    final LeaderCard lead4=new LeaderDepot(12,Resource.COIN, Resource.STONE,"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png");
    @Test
    public void leaderCardView() {
        System.out.println(lead1.view());
        System.out.println(lead2.view());
        System.out.println(lead3.view());
        System.out.println(lead4.view());
    }
}