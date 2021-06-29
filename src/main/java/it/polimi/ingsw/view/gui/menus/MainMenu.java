package it.polimi.ingsw.view.gui.menus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {
    int choice = -1;
    JTextField id = new JTextField();
    JLabel username = new JLabel("Username:");
    final JPanel panel = new JPanel();
    final JFrame frame = new JFrame();
    String[] options = {"Join Game", "Credits", "Quit"};

    public MainMenu(){
        frame.setTitle("Masters of Renaissance");

        JLabel title = new JLabel("Masters of Renaissance");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("", Font.BOLD, 40));

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0,50)));

        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(username);

        id.setMaximumSize(new Dimension(200, 30));

        panel.add(id);
        panel.add(Box.createRigidArea(new Dimension(0,20)));

        //frame.setSize(800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));



    }


    public int prompt(){

        for(String each : options){
            JButton btn = new JButton(each);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(300, btn.getMinimumSize().height));

            btn.addActionListener(this);

            panel.add(btn);
            panel.add(Box.createRigidArea(new Dimension(0,20)));
        }

        frame.setVisible(true);
        frame.add(panel);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        synchronized (this){
            while(this.choice<0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.choice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i< options.length; i++){
            if (options[i].equals(e.getActionCommand())){
                synchronized (this) {
                    this.choice = i+1;
                    this.notify();
                    this.frame.dispose();
                }
            }
        }
    }
}
