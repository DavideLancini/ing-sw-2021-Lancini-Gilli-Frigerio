package it.polimi.ingsw.psp12.view.gui.menus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeadersChoiceMenu extends SubMenu implements ActionListener {

        private final int[] choice;
        private final ImageIcon[] leaders = new ImageIcon[4];
        private final JLabel selected = new JLabel("Selected: ");
        private final JButton[] buttons = new JButton[4];

        public LeadersChoiceMenu(String[] leaders){
            this.choice = new int[2];
            for(int i=0;i< leaders.length;i++)this.leaders[i]= new ImageIcon(leaders[i]);


            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(new EmptyBorder(new Insets(80, 80, 80, 80)));
            this.setHeader("Choose 2 of the following 4 leader cards:");
            panel.add(Box.createRigidArea(new Dimension(0,30)));

            frame.setTitle("Initial Setup");

            JPanel middle = new JPanel();
            middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));
            for(int i=0; i<leaders.length; i++){

                buttons[i] = new JButton(new ImageIcon(this.leaders[i].getImage().getScaledInstance(180,270,Image.SCALE_DEFAULT)));

                buttons[i].addActionListener(this);

                middle.add(buttons[i]);
                middle.add(Box.createRigidArea(new Dimension(5,0)));
            }
            panel.add(middle);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            selected.setAlignmentX(Component.CENTER_ALIGNMENT);
            selected.setFont(new Font(null, Font.PLAIN, 15));
            panel.add(selected);

            JButton done = new JButton("Done");
            done.setPreferredSize(new Dimension(30,20));
            done.addActionListener(this);
            done.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(Box.createRigidArea(new Dimension(0,5)));
            panel.add(done);
        }


        public int[] prompt(){
            this.finalizeAndWait();
            return this.choice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Done") && choice[0] != choice[1]){
                synchronized (this) {
                    this.notify();
                    frame.dispose();
                    return;
                }
            }
            else for(int i=0; i< leaders.length; i++){
                if(buttons[i].equals(e.getSource()) && choice[1] != i){
                    choice[0] = choice[1];
                    choice[1] = i;
                }
            }

            selected.setText("Selected: "+(choice[0]+1)+" and "+(choice[1]+1));
            if(choice[0] == choice[1])selected.setText("Selected: "+(choice[0]+1));

            SwingUtilities.updateComponentTreeUI(frame);
        }


    }



