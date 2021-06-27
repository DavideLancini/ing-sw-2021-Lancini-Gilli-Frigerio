package it.polimi.ingsw.view.gui.menus;

import javax.swing.*;
import java.awt.*;

public abstract class SubMenu {
    JFrame frame =  new JFrame();


    void finalizeAndWait(){
        frame.setVisible(true);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
