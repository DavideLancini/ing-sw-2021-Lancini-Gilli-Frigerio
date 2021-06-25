package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.gui.playerboardPanels.PBPanel;

import javax.swing.*;
import java.util.Arrays;

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
         pb.getStrongbox().deposit(Arrays.asList(Resource.SERVANT, Resource.STONE, Resource.STONE, Resource.SHIELD, Resource.STONE));
         pb.getDefaultProduction().setInput(Resource.STONE, Resource.COIN);
         pb.getDefaultProduction().setOutput(Resource.SERVANT);

         pb.setLeaders(new LeaderCard[]{
                 new LeaderSale(1, Resource.SERVANT, null, "src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png")
                    , new LeaderSale(1, Resource.SERVANT, null, "src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-49-1.png")});
         pb.getLeaderCard(0).toggleActive();

         frame.add(new PBPanel(pb,true));
        frame.pack();
         frame.setVisible(true);

        SwingUtilities.updateComponentTreeUI(frame);
    }
}
