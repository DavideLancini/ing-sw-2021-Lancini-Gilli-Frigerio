package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.network.messages.ClientMessageTakeResources;
import it.polimi.ingsw.view.gui.panels.MarketPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeResourcesMenu extends SubMenu implements ActionListener {

    private final Marble[][] marketBoard;
    private boolean isRow;
    private int position;
    private final JButton[] buttons = new JButton[7];

    public TakeResourcesMenu(Market market) {
        this.marketBoard = market.getMarketBoard();

        for(int i = 0; i< buttons.length; i++){
            buttons[i] = new JButton(MarketPanel.toIcon(market.getSideMarble()));
            buttons[i].addActionListener(this);
        }
    }

    public ClientMessageTakeResources prompt() {
        JLabel title = new JLabel("Choose where to insert the new marble:");
        title.setFont(new Font(null, Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40, 40, 40, 40)));

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0,30)));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Market");

        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(4,5));


        int i = 4;
        for (Marble[] row : this.marketBoard){
            for(Marble each : row){
                middle.add(new JLabel(MarketPanel.toIcon(each)));
            }
            //ROW BUTTONS
            middle.add(buttons[i]);
            i++;
        }

        // COLUMN BUTTONS
        middle.add(buttons[0]);
        middle.add(buttons[1]);
        middle.add(buttons[2]);
        middle.add(buttons[3]);
        middle.add(Box.createRigidArea(new Dimension(0,0)));


        panel.add(middle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));


        frame.add(panel);

        this.finalizeAndWait();

        return new ClientMessageTakeResources(this.isRow, this.position);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action");
        for (int i = 0; i<this.buttons.length; i++){
            if (this.buttons[i].equals(e.getSource())){
                this.isRow = i >= 4;
                this.position = i % 4;
                System.out.println("found "+i);

                synchronized (this) {
                    this.notify();
                    frame.dispose();
                    return;
                }

            }
        }
    }
}
