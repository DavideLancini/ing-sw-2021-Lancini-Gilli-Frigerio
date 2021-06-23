package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class StrdPannel {
    public static void main(String[] args) {
         JFrame frame = new JFrame();
         frame.setTitle("Prova");
         frame.setSize(1000,1000);
         frame.setVisible(true);
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

         PlayerBoard pb = new PlayerBoard();
         pb.getDepot().setContents(new Resource[]{
                Resource.COIN, Resource.SHIELD, Resource.SHIELD, Resource.STONE, Resource.EMPTY, Resource.EMPTY, Resource.SERVANT, Resource.SERVANT, Resource.SERVANT, Resource.SERVANT
         });

         pb.getStrongbox().deposit(Arrays.asList(Resource.SERVANT, Resource.STONE, Resource.STONE, Resource.SHIELD, Resource.STONE));

         frame.add(new PBPanel(pb));
    }
}
