package it.polimi.ingsw.view.gui.panels;

import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Market;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MarketPanel extends JPanel {
    public MarketPanel(Market m) {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JLabel(toIcon(m.getSideMarble())));
        this.add(Box.createRigidArea(new Dimension(40,0)));

        this.setBorder(new EmptyBorder(new Insets(40,40,40,40)));

        JPanel market = new JPanel();
        market.setLayout(new GridLayout(3,4));

        for (Marble[] row : m.getMarketBoard()){
            for(Marble each : row){
                market.add(new JLabel(toIcon(each)));
            }
        }

        this.add(market);



    }

    public static ImageIcon toIcon(Marble marble){
        int x = 24, y = 24;
        switch(marble){
            case PURPLE: return new ImageIcon(new ImageIcon("src/main/resources/Icons/purple.PNG").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
            case BLUE: return new ImageIcon(new ImageIcon("src/main/resources/Icons/blue.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT));
            case YELLOW: return new ImageIcon(new ImageIcon("src/main/resources/Icons/yellow.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT));
            case RED: return new ImageIcon(new ImageIcon("src/main/resources/Icons/red.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT));
            case GRAY: return new ImageIcon(new ImageIcon("src/main/resources/Icons/black.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT));
            case WHITE: return new ImageIcon(new ImageIcon("src/main/resources/Icons/white.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT));
            default: throw new IllegalStateException();
        }
    }
}
