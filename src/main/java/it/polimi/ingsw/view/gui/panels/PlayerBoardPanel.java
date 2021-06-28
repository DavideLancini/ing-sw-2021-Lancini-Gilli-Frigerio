package it.polimi.ingsw.view.gui.panels;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.gui.ResIcons;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardPanel extends JPanel {


    public PlayerBoardPanel(PlayerBoard pb, boolean own){
        super();
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));

        GridBagConstraints c = new GridBagConstraints();
        Insets largegap = new Insets(5,15,25,20);
        Insets smallgap = new Insets(5,15,5,20);

        c.gridx=0;
        c.gridy=0;

        JLabel player = new JLabel("player   "+pb.getFaith(), ResIcons.FAITH.toIcon(), SwingConstants.TRAILING);
        player.setHorizontalTextPosition(JLabel.LEFT);
        player.setFont(new Font(null, Font.BOLD, 20));
        this.add(player,c);


        c.gridy++;
        JLabel depotlabel = new JLabel("Depot:");
        depotlabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(depotlabel,c);

        c.gridy++;
        c.insets = largegap;
        Resource[] depot = new Resource[6];
        for(int i=0; i<depot.length; i++){
            depot[i] = pb.getDepot().getResource(i);
        }
        DepotPanel dpanel = new DepotPanel(depot);
        this.add(dpanel, c);

        c.gridy++;
        c.insets = smallgap;
        JLabel sblabel = new JLabel("Strongbox:");
        sblabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(sblabel, c);

        StrongBoxPanel sbpanel = new StrongBoxPanel(pb.getStrongbox().getResources());
        c.gridy++;
        c.insets = largegap;
        this.add(sbpanel, c);

        c.gridy++;
        c.insets = smallgap;
        JLabel dplabel = new JLabel("Default Production:");
        dplabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(dplabel, c);

        c.gridy++;
        c.insets = largegap;
        DefaultProdPanel dp = new DefaultProdPanel(pb.getDefaultProduction());
        this.add(dp,c);

        c.gridy++;
        c.insets = smallgap;
        JLabel llabel = new JLabel("Leaders:");
        llabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(llabel, c);

        c.gridy++;
        c.insets = largegap;
        LeadersPanel lp = new LeadersPanel(pb.getLeaderCard(0), pb.getLeaderCard(1), own);
        this.add(lp,c);



        //SECOND COLUMN
        c.gridy=0;
        c.gridx=1;
        c.insets = smallgap;
        JLabel clabel = new JLabel("Development Cards:");
        clabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(clabel, c);

        DevCardsPanel cpanel = new DevCardsPanel(pb);
        c.gridy=1;
        c.insets = largegap;
        c.weightx=1;
        c.weighty=1;
        c.gridheight = 10;
        this.add(cpanel, c);

        SwingUtilities.updateComponentTreeUI(this);
    }

}
