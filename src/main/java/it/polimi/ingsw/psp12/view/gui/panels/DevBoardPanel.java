package it.polimi.ingsw.psp12.view.gui.panels;

import it.polimi.ingsw.psp12.model.DevCard;
import it.polimi.ingsw.psp12.model.DevCardBoard;

import javax.swing.*;
import java.awt.*;

public class DevBoardPanel extends JPanel {
    public DevBoardPanel(DevCardBoard db){
        super();
        this.setLayout(new GridLayout(3,0, 5 ,5));

        int x = 100, y = 150;

        DevCard[][] transposed = transpose(db.getTop());


        for(DevCard[] row : transposed){
            for(DevCard each : row){
                this.add(each == null ?
                        new JLabel(new ImageIcon(new ImageIcon("src/main/resources/Icons/back.PNG").getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT)))
                        : new JLabel(new ImageIcon(new ImageIcon(each.getPath()).getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT))));
            }
        }

    }

    public static DevCard[][] transpose(DevCard[][] db){
        DevCard[][] transposed = new DevCard[db[0].length][db.length];

        for(int i = 0; i<db.length; i++){
            for(int j = 0; j<db[0].length; j++){
                transposed[j][i] = db[i][j];
            }
        }
        return transposed;
    }
}
