package it.polimi.ingsw.psp12.view.gui.panels;

import it.polimi.ingsw.psp12.model.PlayerBoard;

import javax.swing.*;
import java.awt.*;

public class DevCardsPanel extends JPanel {
    public DevCardsPanel(PlayerBoard pb){
        super();

        int x = 120;
        int y = 180;


        this.setLayout(new GridLayout(0, 3, 5, 5));

        ImageIcon empty = new ImageIcon(new ImageIcon("src/main/resources/DevCardImg/Masters of Renaissance_Cards_EMPTY.png")
                .getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
        for(int j = 2; j>-1; j--){
            if(pb.getDevCard(0,j) != null || pb.getDevCard(1,j) != null || pb.getDevCard(2,j) != null)
                for(int i=0 ; i<3; i++){
                this.add(pb.getDevCard(i,j) != null ?
                        new JLabel(new ImageIcon(new ImageIcon(
                                pb.getDevCard(i,j).getPath()).getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT)))
                        : new JLabel(empty));
            }
        }

        SwingUtilities.updateComponentTreeUI(this);
    }

}
