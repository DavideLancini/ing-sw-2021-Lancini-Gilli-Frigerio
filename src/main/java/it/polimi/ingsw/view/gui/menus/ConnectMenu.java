package it.polimi.ingsw.view.gui.menus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectMenu extends SubMenu implements ActionListener {

    private final int[] info = new int[3];
    final JTextField address = new JTextField();
    private final JTextField port = new JTextField();
    private final JTextField localport = new JTextField();

    public ConnectMenu(){
        JPanel infopanel = new JPanel();

        infopanel.setLayout(new GridLayout(0,2,10,20));
        infopanel.add(new JLabel("Server Address: "));
        infopanel.add(address);
        infopanel.add(new JLabel("Port: "));
        infopanel.add(port);
        infopanel.add(new JLabel("Local Port: "));
        infopanel.add(localport);

        infopanel.setBorder(new EmptyBorder(new Insets(20, 30, 20, 30)));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(infopanel);

        JButton done = new JButton("Connect");
        done.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setBorder(new EmptyBorder(new Insets(0,0,15,0)));
        done.addActionListener(this);
        panel.add(done);

        frame.setTitle("Connection");
    }


    public int[] prompt(){
        finalizeAndWait();
        return info;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Connect")){
            try{
                info[1] = Integer.parseInt(port.getText());
                info[2] = Integer.parseInt(localport.getText());

                synchronized (this){
                    this.notify();
                    frame.dispose();
                }
            }
            catch (NumberFormatException ignored){}
        }
    }
}
