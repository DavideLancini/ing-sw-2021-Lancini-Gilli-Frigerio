package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.gui.menus.GameMenu;

import javax.swing.*;
import java.util.Arrays;

//USED ONLY TO TEST GUI
@SuppressWarnings("ALL")
public class StrdPannel {
    public static void main(String[] args) throws Exception {
         JFrame frame = new JFrame();
         frame.setTitle("Prova");
         //frame.setSize(1000,1000);
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

         PlayerBoard pb = new PlayerBoard();
         pb.getDepot().setContents(new Resource[]{
                Resource.COIN, Resource.SHIELD, Resource.SHIELD, Resource.STONE, Resource.EMPTY, Resource.EMPTY, Resource.SERVANT, Resource.SERVANT, Resource.SERVANT, Resource.SERVANT
         });
         pb.addDevCard(new DevCard(Level.ONE, CardColor.BLUE, 1, null, null, "src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png"), 0);
        pb.addDevCard(new DevCard(Level.ONE, CardColor.BLUE, 1, null, null, "src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-2-1.png"), 2);
        pb.addDevCard(new DevCard(Level.TWO, CardColor.BLUE, 1, null, null, "src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-3-1.png"), 2);

        pb.getStrongbox().deposit(Arrays.asList(Resource.SERVANT, Resource.STONE, Resource.STONE, Resource.SHIELD, Resource.STONE));
         pb.getDefaultProduction().setInput(Resource.STONE, Resource.COIN);
         pb.getDefaultProduction().setOutput(Resource.SERVANT);

         pb.setLeaders(new LeaderCard[]{
                 new LeaderSale(1, Resource.SERVANT, null, "src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png"),
                 new LeaderProduction(1, Resource.SERVANT, null, null, "src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png")});

         pb.getLeaderCard(0).toggleActive();
        ((LeaderProduction)pb.getLeaderCard(1)).setChoice(Resource.SERVANT);


        Resource[] r1= {Resource.SHIELD,Resource.SERVANT,Resource.SHIELD,Resource.SHIELD};
        Resource[] r2= {Resource.STONE,Resource.STONE};
        Resource[] r3= {Resource.SHIELD,Resource.FAITH,Resource.FAITH};
        Resource[] r4= {Resource.COIN,Resource.COIN};

        Production p1= new Production(r2,r3);
        Production p2= new Production(r1,r4);
        Production p3= new Production(r4,r2);

        DevCard d1= new DevCard(Level.TWO,CardColor.BLUE,9,r1,p1,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png");
        DevCard d2= new DevCard(Level.ONE,CardColor.GREEN,9,r3,p2,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-2-1.png");
        DevCard d3= new DevCard(Level.THREE,CardColor.YELLOW,9,r4,p3,"src/main/resources/DevCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-3-1.png");

        DevCardDeck[][] board={
                {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d3,d2,d1})},
                {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d2,d1,d3})},
                {new DevCardDeck(new DevCard[]{d2,d3,d1}), new DevCardDeck(new DevCard[]{d2,d1,d3}), new DevCardDeck(new DevCard[]{d3,d2,d1})},
                {new DevCardDeck(new DevCard[]{d1,d2,d3}), new DevCardDeck(new DevCard[]{d2,d1,d3}), new DevCardDeck(new DevCard[]{d3,d2,d1})}
        };
        DevCardBoard dcb = new DevCardBoard(board);




        //frame.add(new PlayerBoardPanel(pb,true, "YOU"));
        //frame.add(new MarketPanel(new Market()));
        //new TakeResourcesMenu(new Market()).prompt();
        //new BuyDevCardMenu(dcb.getTop()).prompt();
        //new DefaultProductionMenu(new DefaultProduction()).prompt();
        //new LeaderOptionsMenu(pb.getLeaderCard()).prompt();
        //new ProduceMenu(pb).prompt();
        //new ArrangeMenu(Arrays.asList(Resource.SERVANT, Resource.COIN),1).prompt();
        GameMenu gm = new GameMenu();
        gm.setVisible();
        gm.setWaiting();
        //frame.pack();
        //frame.setVisible(true);
        //SwingUtilities.updateComponentTreeUI(frame);
    }
}
