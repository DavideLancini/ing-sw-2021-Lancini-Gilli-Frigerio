package it.polimi.ingsw.psp12.view.gui.panels;

import it.polimi.ingsw.psp12.model.DefaultProduction;
import it.polimi.ingsw.psp12.model.Resource;
import it.polimi.ingsw.psp12.view.gui.ResIcons;

import javax.swing.*;
import java.awt.*;

public class DefaultProdPanel extends JPanel{
    public DefaultProdPanel(DefaultProduction defaultProduction){
        this(defaultProduction, 24,24);
    }

    public DefaultProdPanel(DefaultProduction defaultProduction, int x, int y) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));

        Resource[] resources = new Resource[]{defaultProduction.getInput()[0], defaultProduction.getInput()[1], defaultProduction.getOutput()[0]};
        ImageIcon[] icons = new ImageIcon[3];

        for(int i=0;i<icons.length;i++){
            switch (resources[i]){
                case SERVANT: icons[i] = ResIcons.SERVANT.toIcon(x,y); break;
                case STONE: icons[i] = ResIcons.STONE.toIcon(x,y); break;
                case SHIELD: icons[i] = ResIcons.SHIELD.toIcon(x,y); break;
                case COIN: icons[i] = ResIcons.COIN.toIcon(x,y); break;
                case EMPTY: icons[i] = ResIcons.EMPTY.toIcon(x,y); break;
            }
        }

        input.add(new JLabel(icons[0]));
        input.add(new JLabel(icons[1]));

        this.add(input);


        this.add(bracket(15,48));

        this.add(new JLabel(icons[2]));

        SwingUtilities.updateComponentTreeUI(this);

    }

    public static JLabel bracket(int x, int y){
        return new JLabel(new ImageIcon(new ImageIcon("src/main/resources/Icons/bracket.png").getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT)));
    }
}
