package it.polimi.ingsw.model;

import org.junit.Test;


public class LeaderCardTest {
    Resource[] cost = {Resource.COIN, Resource.STONE};
    Production production = new Production(cost, cost);
    LeaderCard lead1=new LeaderTransform(12,Resource.COIN, new CardColor[]{CardColor.PURPLE, CardColor.YELLOW});
    LeaderCard lead2=new LeaderProduction(12,Resource.COIN, CardColor.BLUE, production);
    LeaderCard lead3=new LeaderSale(12,Resource.COIN, new CardColor[]{CardColor.BLUE, CardColor.BLUE, CardColor.GREEN});
    LeaderCard lead4=new LeaderDepot(12,Resource.COIN, Resource.STONE);
    @Test
    public void leaderCardView() {
        lead1.leaderCardView();
        lead2.leaderCardView();
        lead3.leaderCardView();
        lead4.leaderCardView();
    }
}