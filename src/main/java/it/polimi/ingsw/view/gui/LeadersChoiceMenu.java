package it.polimi.ingsw.view.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeadersChoiceMenu implements ActionListener {

        private final JFrame frame = new JFrame();
        final int[] choice;
        private final String[] leaders;

        public LeadersChoiceMenu(String[] leaders){
            this.choice = new int[2];
            this.leaders = leaders;
        }


        public void prompt(){



            JPanel panel = new JPanel();
            JLabel title = new JLabel("Choose 2 of the following 4 leader cards:");
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setFont(new Font("", Font.PLAIN, 10));

            frame.add(title);
            panel.add(Box.createRigidArea(new Dimension(0,50)));


            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(new EmptyBorder(new Insets(80, 80, 80, 80)));


            for(String each : leaders){
                JButton btn = new JButton(each);
                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                btn.setMaximumSize(new Dimension(300, 200));

                btn.addActionListener(this);

                panel.add(btn);
                panel.add(Box.createRigidArea(new Dimension(0,20)));
            }
            JLabel selected = new JLabel("Selected: ");
            panel.add(selected);

            JButton done = new JButton("Done");
            done.addActionListener(this);
            panel.add(done);


            frame.setVisible(true);
            frame.add(panel);

            synchronized (this){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Done")){
                synchronized (this) {
                    this.notify();
                    frame.dispose();
                }
            }
            else for(int i=0; i< leaders.length; i++){
                if(leaders[i].equals(e.getActionCommand()) && choice[1] != i){
                    choice[0] = choice[1];
                    choice[1] = i;
                }
            }
        }


    }



