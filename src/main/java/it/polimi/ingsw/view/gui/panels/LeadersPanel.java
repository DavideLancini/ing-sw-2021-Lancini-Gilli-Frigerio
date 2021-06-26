package it.polimi.ingsw.view.gui.panels;

import it.polimi.ingsw.model.LeaderCard;

import javax.swing.*;
import java.awt.*;

public class LeadersPanel extends JPanel {
    public LeadersPanel(LeaderCard lc1, LeaderCard lc2, boolean own) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        int x = 100;
        int y = 150;

        ImageIcon covered = new ImageIcon(new ImageIcon("src/main/resources/Icons/back.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT));

        if(lc1 != null){
            ImageIcon i1 = new ImageIcon(new ImageIcon(lc1.getPath()).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
            this.add(lc1.getIsActive() ? new JLabel(i1)
                            : own ? new JLabel(new ImageIcon(GrayFilter.createDisabledImage(i1.getImage())))
                                    : new JLabel(covered));
        }

        this.add(Box.createRigidArea(new Dimension(5,0)));

        if(lc2 != null){
            ImageIcon i2 = new ImageIcon(new ImageIcon(lc2.getPath()).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
            this.add(lc2.getIsActive() ? new JLabel(i2)
                        : own ? new JLabel(new ImageIcon(GrayFilter.createDisabledImage(i2.getImage())))
                            : new JLabel(covered));
        }

    }
}
