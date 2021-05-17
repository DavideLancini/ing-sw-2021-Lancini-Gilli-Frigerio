package it.polimi.ingsw.model;

import org.junit.Test;


public class LeaderCardTest {
    Resource[] cost = {Resource.COIN, Resource.STONE};
    Production production = new Production(cost, cost);
    LeaderCard lead1=new LeaderTransform(12,Resource.COIN, new Resource[]{Resource.STONE, Resource.STONE});
    LeaderCard lead2=new LeaderProduction(12,Resource.COIN, new Resource[]{Resource.STONE, Resource.STONE},production);
    LeaderCard lead3=new LeaderSale(12,Resource.COIN, new Resource[]{Resource.STONE, Resource.STONE});
    LeaderCard lead4=new LeaderDepot(12,Resource.COIN, new Resource[]{Resource.STONE, Resource.STONE});
    @Test
    public void leaderCardView() {
        lead1.leaderCardView();
        lead2.leaderCardView();
        lead3.leaderCardView();
        lead4.leaderCardView();
    }
}