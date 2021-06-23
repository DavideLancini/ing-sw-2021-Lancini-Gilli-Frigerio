package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class SBPanel extends JPanel {
    public SBPanel(Collection<Resource> resources) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel labelbegin = new JLabel("[");
        labelbegin.setFont(new Font(null, Font.BOLD, 30));
        this.add(labelbegin);
        this.add(Box.createRigidArea(new Dimension(10, 0)));

        int servant = 0;int shield = 0;int coin = 0;int stone = 0;
        for (Resource resource : resources) {
            if (resource == Resource.STONE)
                stone++;
            else if (resource == Resource.SERVANT)
                servant++;
            else if (resource == Resource.COIN)
                coin++;
            else if (resource == Resource.SHIELD)
                shield++;
        }
        if (servant > 0) {
            JLabel label = new JLabel(String.valueOf(servant));
            label.setFont(new Font(null, Font.BOLD, 15));
            this.add(label);
            this.add(new JLabel(ResIcons.SERVANT.toIcon()));
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        if (coin > 0) {
            JLabel label = new JLabel(String.valueOf(coin));
            label.setFont(new Font(null, Font.BOLD, 15));
            this.add(label);
            this.add(new JLabel(ResIcons.COIN.toIcon()));
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        if (shield > 0) {
            JLabel label = new JLabel(String.valueOf(shield));
            label.setFont(new Font(null, Font.BOLD, 15));
            this.add(label);
            this.add(new JLabel(ResIcons.SHIELD.toIcon()));
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        if (stone> 0) {
            JLabel label = new JLabel(String.valueOf(stone));
            label.setFont(new Font(null, Font.BOLD, 15));
            this.add(label);
            this.add(new JLabel(ResIcons.STONE.toIcon()));
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        JLabel labelend = new JLabel("]");
        labelend.setFont(new Font(null, Font.BOLD, 30));
        this.add(labelend);


        SwingUtilities.updateComponentTreeUI(this);
    }
}
