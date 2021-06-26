package it.polimi.ingsw.view.gui.panels;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.gui.ResIcons;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class StrongBoxPanel extends JPanel {
    public StrongBoxPanel(Collection<Resource> resources) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel labelbegin = new JLabel("[");
        labelbegin.setFont(new Font(null, Font.BOLD, 25));
        this.add(labelbegin);
        this.add(Box.createRigidArea(new Dimension(10, 0)));

        JLabel empty = new JLabel("Empty");
        this.add(empty);

        Component space =  Box.createRigidArea(new Dimension(10, 0));
        this.add(space);

        int x = 24, y=24;

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
            this.remove(empty);
            this.remove(space);
            JLabel label = new JLabel(String.valueOf(servant));
            label.setFont(new Font(null, Font.BOLD, 15));
            this.add(label);
            this.add(new JLabel(ResIcons.SERVANT.toIcon(x,y)));
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        if (coin > 0) {
            this.remove(empty);
            this.remove(space);
            JLabel label = new JLabel(String.valueOf(coin));
            label.setFont(new Font(null, Font.BOLD, 15));
            this.add(label);
            this.add(new JLabel(ResIcons.COIN.toIcon(x,y)));
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        if (shield > 0) {
            this.remove(empty);
            this.remove(space);
            JLabel label = new JLabel(String.valueOf(shield));
            label.setFont(new Font(null, Font.BOLD, 15));
            this.add(label);
            this.add(new JLabel(ResIcons.SHIELD.toIcon(x,y)));
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        if (stone> 0) {
            this.remove(empty);
            this.remove(space);
            JLabel label = new JLabel(String.valueOf(stone));
            label.setFont(new Font(null, Font.BOLD, 15));
            this.add(label);
            this.add(new JLabel(ResIcons.STONE.toIcon(x,y)));
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        JLabel labelend = new JLabel("]");
        labelend.setFont(new Font(null, Font.BOLD, 25));
        this.add(labelend);


        SwingUtilities.updateComponentTreeUI(this);
    }
}
