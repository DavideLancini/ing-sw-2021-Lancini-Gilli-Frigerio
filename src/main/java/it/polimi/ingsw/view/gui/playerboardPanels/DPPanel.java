package it.polimi.ingsw.view.gui.playerboardPanels;

import it.polimi.ingsw.model.DefaultProduction;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;

public class DPPanel extends JPanel{
    public DPPanel(DefaultProduction defaultProduction) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));

        Resource[] resources = new Resource[]{defaultProduction.getInput()[0], defaultProduction.getInput()[1], defaultProduction.getOutput()[0]};
        ImageIcon[] icons = new ImageIcon[3];

        for(int i=0;i<icons.length;i++){
            switch (resources[i]){
                case SERVANT: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Servant.PNG"); break;
                case STONE: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Stone.PNG"); break;
                case SHIELD: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Shield.PNG"); break;
                case COIN: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Coin.PNG"); break;
                case EMPTY: icons[i] = new ImageIcon("src/main/resources/ResourceIcons/Empty.PNG"); break;
            }
        }

        input.add(new JLabel(icons[0]));
        input.add(new JLabel(icons[1]));

        this.add(input);


        this.add(new JLabel(new ImageIcon(new ImageIcon("src/main/resources/ResourceIcons/bracket.png").getImage().getScaledInstance(20, 64, Image.SCALE_DEFAULT))));

        this.add(new JLabel(icons[2]));

        SwingUtilities.updateComponentTreeUI(this);

    }
}
