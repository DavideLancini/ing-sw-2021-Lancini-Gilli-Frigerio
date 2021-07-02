package it.polimi.ingsw.psp12.view.gui.panels;

import it.polimi.ingsw.psp12.model.LeaderCard;

import javax.swing.*;
import java.awt.*;

public class LeadersPanel extends JPanel {
    public LeadersPanel(LeaderCard lc1, LeaderCard lc2, boolean own) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.X_AXIS));
        JPanel label = new JPanel();
        label.setLayout(new BoxLayout(label, BoxLayout.X_AXIS));

        int x = 100;
        int y = 150;

        ImageIcon covered = new ImageIcon(new ImageIcon("src/main/resources/Icons/back.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT));

        if(lc1 != null){
            ImageIcon i1 = new ImageIcon(new ImageIcon(lc1.getPath()).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
            cards.add(own || lc1.getIsActive() ? new JLabel(i1) : new JLabel(covered));
            cards.add(Box.createRigidArea(new Dimension(5,0)));
        }

        if(lc2 != null){
            ImageIcon i2 = new ImageIcon(new ImageIcon(lc2.getPath()).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
            cards.add(own || lc2.getIsActive() ? new JLabel(i2) : new JLabel(covered));
        }

        if(own){
            if(lc1 != null)label.add(new JLabel(lc1.getIsActive() ? "        Active        " : "        Inactive        "));
            if(lc2 != null)label.add(new JLabel(lc2.getIsActive() ? "        Active        " : "        Inactive        "));
        }


        this.add(cards);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(label);
    }
}
