package it.polimi.ingsw.psp12.view.gui.panels;

import it.polimi.ingsw.psp12.model.*;
import it.polimi.ingsw.psp12.view.gui.ResIcons;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardPanel extends JPanel {

    public PlayerBoardPanel(PlayerBoard pb, boolean own, String id){
        super();
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));

        GridBagConstraints c = new GridBagConstraints();
        Insets largeGap = new Insets(5,15,25,20);
        Insets smallGap = new Insets(5,15,5,20);

        c.gridx=0;
        c.gridy=0;

        JLabel player = new JLabel(id+"   "+pb.getFaith(), ResIcons.FAITH.toIcon(), SwingConstants.TRAILING);
        player.setHorizontalTextPosition(JLabel.LEFT);
        player.setFont(new Font(null, Font.BOLD, 20));
        this.add(player,c);


        c.gridy++;
        JLabel depotLabel = new JLabel("Depot:");
        depotLabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(depotLabel,c);

        c.gridy++;
        c.insets = largeGap;
        Resource[] depot = new Resource[10];
        for(int i=0; i<depot.length; i++){
            depot[i] = pb.getDepot().getResource(i);
        }
        DepotPanel dPanel = new DepotPanel(depot);
        this.add(dPanel, c);

        c.gridy++;
        c.insets = smallGap;
        JLabel sbLabel = new JLabel("Strongbox:");
        sbLabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(sbLabel, c);

        StrongBoxPanel sbPanel = new StrongBoxPanel(pb.getStrongbox().getResources());
        c.gridy++;
        c.insets = largeGap;
        this.add(sbPanel, c);

        c.gridy++;
        c.insets = smallGap;
        JLabel dpLabel = new JLabel("Default Production:");
        dpLabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(dpLabel, c);

        c.gridy++;
        c.insets = largeGap;
        DefaultProdPanel dp = new DefaultProdPanel(pb.getDefaultProduction());
        this.add(dp,c);

        c.gridy++;
        c.insets = smallGap;
        JLabel lLabel = new JLabel("Leaders:");
        lLabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(lLabel, c);

        c.gridy++;
        c.insets = largeGap;
        LeadersPanel lp = new LeadersPanel(pb.getLeaderCard(0), pb.getLeaderCard(1), own);
        this.add(lp,c);



        //SECOND COLUMN
        c.gridy=0;
        c.gridx=1;
        c.insets = smallGap;
        JLabel cLabel = new JLabel("Development Cards:");
        cLabel.setFont(new Font(null, Font.BOLD, 15));
        this.add(cLabel, c);

        DevCardsPanel cpanel = new DevCardsPanel(pb);
        c.gridy=1;
        c.insets = largeGap;
        c.weightx=1;
        c.weighty=1;
        c.gridheight = 10;
        this.add(cpanel, c);

        SwingUtilities.updateComponentTreeUI(this);
    }

}
