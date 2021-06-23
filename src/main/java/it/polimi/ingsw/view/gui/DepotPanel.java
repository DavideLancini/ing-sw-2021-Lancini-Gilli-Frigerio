package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;

public class DepotPanel extends JPanel {

    public DepotPanel(Resource[] depot){
        super();
        this.setLayout(new GridBagLayout());

        ImageIcon[] icons = new ImageIcon[depot.length];

        for(int i=0;i<depot.length;i++){
            switch (depot[i]){
                case SERVANT: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Servant.PNG"); break;
                case STONE: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Stone.PNG"); break;
                case SHIELD: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Shield.PNG"); break;
                case COIN: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Coin.PNG"); break;
                case EMPTY: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Empty.PNG"); break;
            }
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        this.add(new JLabel(icons[0]), c);
        c.gridx = 1;
        c.gridy = 1;
        this.add(new JLabel(icons[1]), c);
        c.gridx = 3;
        c.gridy = 1;
        this.add(new JLabel(icons[2]), c);
        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel(icons[3]), c);
        c.gridx = 2;
        c.gridy = 2;
        this.add(new JLabel(icons[4]), c);
        c.gridx = 4;
        c.gridy = 2;
        this.add(new JLabel(icons[5]), c);


    }

}
