package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.DevCard;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.network.messages.ClientMessageBuyDevCard;
import it.polimi.ingsw.view.gui.ChoiceBox;
import it.polimi.ingsw.view.gui.panels.DevBoardPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyDevCardMenu extends SubMenu implements ActionListener {

    private Level level;
    private CardColor color;
    private int column;
    private final JButton[] buttons = new JButton[12];

    public BuyDevCardMenu(DevCard[][] devCardBoard) {

        int x = 120, y = 180;

        DevCard[][] board = DevBoardPanel.transpose(devCardBoard);
        int i = 0;
        for(DevCard[] row : board){
            for(DevCard each : row){
                buttons[i] = new JButton(new ImageIcon(new ImageIcon(each.getPath()).getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT)));
                buttons[i].addActionListener(this);
                i++;
            }
        }

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40, 40, 40, 40)));

        this.setHeader("Choose which Development Card to buy:");

        frame.setTitle("Development Cards Board");

        panel.add(Box.createRigidArea(new Dimension(0,15)));

        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(3,4, 5, 5));

        for(JButton each : buttons)middle.add(each);

        panel.add(middle);
    }


    public ClientMessageBuyDevCard prompt(){
        this.finalizeAndWait();
        return new ClientMessageBuyDevCard(level, color, column);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i<this.buttons.length; i++){
            if (this.buttons[i].equals(e.getSource())){
                this.level = Level.values()[i/4];
                this.color = CardColor.values()[i%4];

                this.column = ChoiceBox.prompt("In which column of your board do you want to put this card?", "Placement",
                        new String[]{"First Column", "Second Column", "Third Column"});
                if(this.column < 0)return;

                synchronized (this) {
                    this.notify();
                    frame.dispose();
                    return;
                }

            }
        }
    }
}
