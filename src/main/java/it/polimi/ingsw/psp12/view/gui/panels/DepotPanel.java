package it.polimi.ingsw.psp12.view.gui.panels;

import it.polimi.ingsw.psp12.model.Resource;
import it.polimi.ingsw.psp12.view.gui.ResIcons;

import javax.swing.*;
import java.awt.*;

public class DepotPanel extends JPanel {

    public DepotPanel(Resource[] depot){
        super();
        this.setLayout(new GridBagLayout());

        ImageIcon[] icons = new ImageIcon[depot.length];

        int x = 24, y = 24;


        for(int i=0;i<depot.length;i++){
            switch (depot[i]){
                case SERVANT: icons[i] = ResIcons.SERVANT.toIcon(x,y); break;
                case STONE: icons[i] = ResIcons.STONE.toIcon(x,y); break;
                case SHIELD: icons[i] = ResIcons.SHIELD.toIcon(x,y); break;
                case COIN: icons[i] = ResIcons.COIN.toIcon(x,y); break;
                case EMPTY: icons[i] = ResIcons.EMPTY.toIcon(x,y); break;
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

        c.gridy = 3;
        c.insets = new Insets(10,0,0,0);
        if(depot[6] != Resource.EMPTY){
            c.gridx = 0;
            this.add(new JLabel(icons[6]),c);
            c.gridx = 1;
            this.add(new JLabel(icons[7]),c);
        }
        if(depot[8] != Resource.EMPTY){
            c.gridx = 3;
            this.add(new JLabel(icons[8]),c);
            c.gridx = 4;
            this.add(new JLabel(icons[9]),c);
        }

    }

}
