package it.polimi.ingsw.psp12.view.gui.menus;

import javax.swing.*;
import java.awt.*;

public abstract class SubMenu extends JFrame{
    final JFrame frame = this;
    final JPanel panel = new JPanel();

    void finalizeAndWait(){
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void setHeader(String s){
        JLabel title = new JLabel(s);
        title.setFont(new Font(null, Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
    }


}
