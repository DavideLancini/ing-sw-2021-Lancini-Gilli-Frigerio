package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.model.*;

import javax.swing.*;
import java.awt.*;

public class PBPanel extends JPanel {

    public PBPanel(){
        super();
    }

    public PBPanel(PlayerBoard pb){
        super();
        this.setLayout(new GridBagLayout());

        Resource[] depot = new Resource[6];
        for(int i=0; i<depot.length; i++){
            depot[i] = pb.getDepot().getResource(i);
        }
        DepotPanel dpanel = new DepotPanel(depot);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        this.add(dpanel, c);

        SBPanel sbpanel = new SBPanel(pb.getStrongbox().getResources());
        c.gridy=2;
        this.add(sbpanel, c);


        SwingUtilities.updateComponentTreeUI(this);
    }

    public static void main(String args[]){
        JFrame frame = new JFrame();
        frame.setSize(1920,1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        LeaderCard[] leaders = new LeaderCard[]{new LeaderDepot(1, Resource.COIN, Resource.SHIELD),
                new LeaderSale(2, Resource.SHIELD, new CardColor[]{CardColor.PURPLE})};
        PlayerBoard pb = new PlayerBoard(leaders);

        frame.add(new PBPanel(pb));
    }

}
