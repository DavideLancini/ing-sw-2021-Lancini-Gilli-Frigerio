package it.polimi.ingsw.view.gui.panels;

import it.polimi.ingsw.view.gui.ResIcons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SoloPanel extends JPanel {

    public SoloPanel(String view) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBorder(new EmptyBorder(new Insets(0,10,0,10)));

        String separator = "0m";
        String faith = view.split(separator)[view.split(separator).length-1];

        JLabel player = new JLabel("Lorenzo il Magnifico:   "+faith, ResIcons.FAITH.toIcon(), SwingConstants.TRAILING);
        player.setHorizontalTextPosition(JLabel.LEFT);
        player.setFont(new Font(null, Font.BOLD, 15));
        player.setAlignmentX(CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(10,10)));
        this.add(player);

        this.add(Box.createRigidArea(new Dimension(0, 5)));

        String action = view.split("\n",3)[1];
        ImageIcon icon = null;

        switch (action){
            case "┃    +2\u001b[38;5;9m\uD83D\uDD47\u001b[0m    ┃":
                icon = new ImageIcon("src/main/resources/Icons/cerchio5.png");
                break;
            case "┃ +1\u001b[38;5;9m\uD83D\uDD47\u001b[0m + shuffle ┃":
                icon = new ImageIcon("src/main/resources/Icons/cerchio7.png");
                break;
            case "┃    -2\u001b[38;5;34m██\u001b[0m    ┃":
                icon = new ImageIcon("src/main/resources/Icons/cerchio2.png");
                break;
            case "┃    -2\u001b[38;5;11m██\u001b[0m    ┃":
                icon = new ImageIcon("src/main/resources/Icons/cerchio4.png");
                break;
            case "┃    -2\u001b[38;5;12m██\u001b[0m    ┃":
                icon = new ImageIcon("src/main/resources/Icons/cerchio1.png");
                break;
            case "┃    -2\u001b[38;5;54m██\u001b[0m    ┃":
                icon = new ImageIcon("src/main/resources/Icons/cerchio3.png");
                break;
        }
        if(icon != null) {
            JLabel token = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
            token.setAlignmentX(CENTER_ALIGNMENT);
            this.add(token);
        }
        this.add(Box.createRigidArea(new Dimension(0,10)));

    }
}

/*
    BLUE("\u001b[38;5;12m██\u001b[0m"),
    YELLOW("\u001b[38;5;11m██\u001b[0m"),
    GREEN("\u001b[38;5;34m██\u001b[0m"),
    PURPLE("\u001b[38;5;54m██\u001b[0m");
 */