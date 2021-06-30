package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageLeaderActivation;
import it.polimi.ingsw.network.messages.ClientMessageSellLeader;
import it.polimi.ingsw.network.messages.ClientMessageSetResource;
import it.polimi.ingsw.view.gui.ChoiceBox;
import it.polimi.ingsw.view.gui.ResIcons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderOptionsMenu extends SubMenu implements ActionListener {

    private final JButton[] buttons = new JButton[6];
    private ClientMessage message;

    public LeaderOptionsMenu(LeaderCard[] leaderCards) {
        int x = 150, y = 225;


        buttons[0] = new JButton("Activate Leader");
        buttons[2] = new JButton("Discard Leader");
        buttons[1] = new JButton("Activate Leader");
        buttons[3] = new JButton("Discard Leader");
        buttons[4] = new JButton("Set Produced Resource");
        buttons[5] = new JButton("Set Produced Resource");

        //if(production(leaderCards))


        for (JButton button : buttons) button.addActionListener(this);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(20, 40, 40, 40)));


        this.setHeader("Choose which action to perform:");
        panel.add(Box.createRigidArea(new Dimension(0,15)));
        frame.setTitle("Leader Cards Options");

        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.X_AXIS));
        cards.add(Box.createRigidArea(new Dimension(30,0)));


        for(LeaderCard each : leaderCards){
            cards.add(new JLabel(new ImageIcon(
                    (each != null ? new ImageIcon(each.getPath()).getImage() : new ImageIcon("src/main/resources/Icons/back.PNG").getImage())
                    .getScaledInstance(x,y, Image.SCALE_DEFAULT))));
            cards.add(Box.createRigidArea(new Dimension(30,0)));
        }

        panel.add(cards);
        panel.add(Box.createRigidArea(new Dimension(0,10)));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,30,5,30);
        c.gridy = 0;
        c.gridx = 0;
        for(int i = 0; i<4; i++){
            buttonPanel.add(buttons[i],c);
            c.gridx++;
            if(c.gridx == 2){c.gridx = 0; c.gridy++;}
        }

        if(isProduction(leaderCards[0]))buttonPanel.add(buttons[4],c);
        c.gridx = 1;
        if(isProduction(leaderCards[1]))buttonPanel.add(buttons[5],c);



        panel.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i<this.buttons.length; i++){
            if (this.buttons[i].equals(e.getSource())){
                if (i >= 4) {
                    int choice = ChoiceBox.prompt("Which Resource do you want to substitute it with?", "Options",
                            new ImageIcon[]{ResIcons.STONE.toIcon(), ResIcons.SERVANT.toIcon(), ResIcons.COIN.toIcon(), ResIcons.SHIELD.toIcon(), ResIcons.EMPTY.toIcon()});
                    if(choice < 0)return;
                    this.message = new ClientMessageSetResource(Resource.values()[choice],i-1);
                }
                else if(i >= 2) {
                    int choice = ChoiceBox.prompt("Are you sure you want to discard this Leader for 1 Faith point?", "Confirm Discard",
                            new String[]{"Yes, Discard", "No, Cancel"});
                    if (choice != 0) return;
                    this.message = new ClientMessageSellLeader(i-2);
                }
                else{
                    this.message = new ClientMessageLeaderActivation(i);
                }
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
        return message;
    }

    public static boolean isProduction(LeaderCard leader){
        if(leader == null)return false;
        String[] paths = {"src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-61-1.png",
                "src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-62-1.png",
                "src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-63-1.png",
                "src/main/resources/LeaderCardImg/Masters of Renaissance_Cards_FRONT_3mmBleed_1-64-1.png"
        };
        for(String each : paths){
            if(leader.getPath().equals(each))return true;
        }
        return false;
    }
}
