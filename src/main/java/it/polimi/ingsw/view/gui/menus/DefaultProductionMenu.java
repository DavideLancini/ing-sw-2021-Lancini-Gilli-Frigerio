package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.DefaultProduction;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageSetResource;
import it.polimi.ingsw.view.gui.ChoiceBox;
import it.polimi.ingsw.view.gui.ResIcons;
import it.polimi.ingsw.view.gui.panels.DefaultProdPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefaultProductionMenu extends SubMenu implements ActionListener {

    private Resource resource;
    private int position;
    private final JButton[] buttons = new JButton[3];

    public DefaultProductionMenu(DefaultProduction defaultProduction) {
        for(int i=0; i<this.buttons.length; i++){
            buttons[i] = new JButton(i == 2 ? defaultProduction.getOutput()[0].toIcon() : defaultProduction.getInput()[i].toIcon());
            buttons[i].addActionListener(this);
        }


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40, 40, 40, 40)));

        this.setHeader("Choose which resource to set:");

        frame.setTitle("Default Production Options");

        panel.add(Box.createRigidArea(new Dimension(0,15)));

        JPanel board = new JPanel();
        board.setLayout(new BoxLayout(board, BoxLayout.X_AXIS));

        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
        input.add(buttons[0]);
        input.add(Box.createRigidArea(new Dimension(0,5)));
        input.add(buttons[1]);

        board.add(input);
        board.add(Box.createRigidArea(new Dimension(10,0)));
        board.add(DefaultProdPanel.bracket(15,60));
        board.add(Box.createRigidArea(new Dimension(10,0)));

        board.add(buttons[2]);

        panel.add(board);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i<this.buttons.length; i++){
            if (this.buttons[i].equals(e.getSource())){

                this.position = i;

                int choice = ChoiceBox.prompt("Which Resource do you want to substitute it with?", "Options",
                        new ImageIcon[]{ResIcons.STONE.toIcon(), ResIcons.SERVANT.toIcon(), ResIcons.COIN.toIcon(), ResIcons.SHIELD.toIcon(), ResIcons.EMPTY.toIcon()});
                if(choice < 0)return;
                this.resource = Resource.values()[choice];

                synchronized (this) {
                    this.notify();
                    frame.dispose();
                    return;
                }

            }
        }

    }

    public ClientMessage prompt() {
        this.finalizeAndWait();
        return new ClientMessageSetResource(resource, position);
    }
}
