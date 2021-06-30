package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageProduce;
import it.polimi.ingsw.view.gui.panels.DefaultProdPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProduceMenu extends SubMenu implements ActionListener {

    private final boolean[] activated = new boolean[6];
    private final JButton[] buttons = new JButton[6];

    public ProduceMenu(PlayerBoard playerBoard) {
        int x = 150, y = 225;
        panel.setBorder(new EmptyBorder(new Insets(30, 40, 40, 40)));

        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,7,5,7);
        grid.add(new DefaultProdPanel(playerBoard.getDefaultProduction(),32,32),c);

        for(int i = 0; i<3; i++){
            c.gridx++;
            grid.add(new JLabel(new ImageIcon(new ImageIcon(
                    playerBoard.getDevCard(i) != null ? playerBoard.getDevCard(i).getPath() : "src/main/resources/DevCardImg/Masters of Renaissance_Cards_EMPTY.png"
            ).getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT))),c);
        }

        for(int i = 0; i<2; i++){
            c.gridx++;
            grid.add(new JLabel(new ImageIcon(new ImageIcon(
                    playerBoard.getLeaderCard(i) != null ? playerBoard.getLeaderCard(i).getPath() : "src/main/resources/Icons/back.PNG"
            ).getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT))),c);
        }

        c.gridy = 1;
        c.gridx = 0;
        c.weighty = 0;

        buttons[0] = new JButton("Disabled");
        buttons[0].addActionListener(this);
        grid.add(buttons[0],c);
        c.gridx++;

        for(int i=1; i<4; i++){
            buttons[i] = new JButton("Disabled");
            buttons[i].addActionListener(this);
            if(playerBoard.getDevCard(i-1) != null)grid.add(buttons[i],c);
            c.gridx++;
        }

        for(int i = 4; i<6; i++){
            buttons[i] = new JButton("Disabled");
            buttons[i].addActionListener(this);
            if(playerBoard.getLeaderCard(i-4) != null
                    && LeaderOptionsMenu.isProduction(playerBoard.getLeaderCard(i-4))
                    && playerBoard.getLeaderCard(i-4).getIsActive())
                grid.add(buttons[i],c);
            c.gridx++;
        }

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.setHeader("Choose which Productions to activate:");
        panel.add(Box.createRigidArea(new Dimension(0,15)));
        panel.add(grid);
        panel.add(Box.createRigidArea(new Dimension(0,25)));
        JButton done = new JButton("Done");
        done.setFont(new Font(null, Font.BOLD, 15));
        done.addActionListener(this);
        done.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(done);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Done")){
            for(boolean each : activated){
                if(each){
                    synchronized (this) {
                        this.notify();
                        frame.dispose();
                        return;
                    }
                }
            }
        }
        else for(int i=0; i<buttons.length; i++){
            if(buttons[i].equals(e.getSource())){
                activated[i] = !activated[i];
                buttons[i].setText(activated[i] ? "Enabled" : "Disabled");
            }
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public ClientMessage prompt() {
        this.finalizeAndWait();
        return new ClientMessageProduce(activated);
    }
}
