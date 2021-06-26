package it.polimi.ingsw.view.gui.panels;

import it.polimi.ingsw.model.DevCard;
import it.polimi.ingsw.model.DevCardBoard;

import javax.swing.*;
import java.awt.*;

public class DevBoardPanel extends JPanel {
    public DevBoardPanel(DevCardBoard db){
        super();
        this.setLayout(new GridLayout(3,0, 5 ,5));

        int x = 100, y = 150;

        DevCard[][] trasposed = new DevCard[db.getTop()[0].length][db.getTop().length];

        for(int i = 0; i<db.getTop().length; i++){
            for(int j = 0; j<db.getTop()[0].length; j++){
                trasposed[j][i] = db.getTop()[i][j];
            }
        }


        for(DevCard[] row : trasposed){
            for(DevCard each : row){
                this.add(each == null ?
                        new JLabel(new ImageIcon(new ImageIcon("src/main/resources/Icons/back.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT)))
                        : new JLabel(new ImageIcon(new ImageIcon(each.getPath()).getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT))));
            }
        }

    }
}
