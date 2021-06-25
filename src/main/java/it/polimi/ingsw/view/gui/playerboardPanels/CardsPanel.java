package it.polimi.ingsw.view.gui.playerboardPanels;

import it.polimi.ingsw.model.PlayerBoard;

import javax.swing.*;
import java.awt.*;

public class CardsPanel extends JPanel {
    public CardsPanel(PlayerBoard pb){
        super();

        int x = 120;
        int y = 180;


        this.setLayout(new GridLayout(3, 3, 5, 5));

        ImageIcon empty = new ImageIcon(new ImageIcon("src/main/resources/DevCardImg/Masters of Renaissance_Cards_EMPTY.png")
                .getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
        for(int j = 2; j>-1; j--){
            for(int i=0 ; i<3; i++){
                this.add(pb.getDevCard(i,j) != null ?
                        new JLabel(new ImageIcon(new ImageIcon(
                                pb.getDevCard(i,j).getImage()).getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT)))
                        : new JLabel(empty));
            }
        }

        SwingUtilities.updateComponentTreeUI(this);
    }

}
