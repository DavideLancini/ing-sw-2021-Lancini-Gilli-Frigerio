package it.polimi.ingsw.psp12.view.gui.menus;

import it.polimi.ingsw.psp12.model.Resource;
import it.polimi.ingsw.psp12.view.gui.panels.StrongBoxPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class ArrangeMenu extends SubMenu implements ActionListener {

    private Resource choice;
    private final JButton[] buttons = new JButton[5];

    public ArrangeMenu(Collection<Resource> resources, int n){

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(10,10,10,10)));

        JLabel top = new JLabel("Resources to be arranged:");
        top.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(top);

        JPanel res = new StrongBoxPanel(resources);
        res.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(res);
        panel.add(Box.createRigidArea(new Dimension(0,15)));

        JLabel label = new JLabel("Choose which resource to put in row n° "+n+" :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.X_AXIS));
        for(int i = 0;i<buttons.length; i++){
            buttons[i] = new JButton(Resource.values()[i].toIcon());
            buttonpanel.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttonpanel.add(Box.createRigidArea(new Dimension(5,0)));
        }

        panel.add(label);
        frame.setTitle("Arranging resources in your depot");
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(buttonpanel);
    }

    public Resource prompt(){
        finalizeAndWait();
        return choice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i<this.buttons.length; i++){
            if (this.buttons[i].equals(e.getSource())){
                this.choice = Resource.values()[i];
                synchronized (this) {
                    this.notify();
                    frame.dispose();
                    return;
                }

            }
        }

    }
}
