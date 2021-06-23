package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageEndTurn;
import it.polimi.ingsw.network.messages.ServerMessageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu implements ActionListener {
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private JLabel wait = new JLabel("Waiting for your turn...");
    private PBPanel pbpanel = new PBPanel();
    private final GridBagConstraints pbc = new GridBagConstraints();

    public GameMenu(){
        //order elements around
        panel.add(this.wait);
        frame.add(panel);
        frame.setVisible(false);
        frame.setSize(1920,1080);
        panel.setLayout(new GridBagLayout());
        pbc.gridy = 1;
        pbc.gridx = 2;


    }

    public void setVisible(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }


    public void display(ServerMessageView opanel) {
        //activate elements
        panel.remove(this.wait);
        switch(opanel.getElem()){
            case PB: displayPB(Serializer.deserializePB(opanel.getView(false))); break;
            case OtherPB: displayOPB(Serializer.deserializePB(opanel.getView(false))); break;
            case Market: displayMarket(Serializer.deserializeMarket(opanel.getView(false))); break;
            //TODO: devboard
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //interactions

    public ClientMessage turn(boolean action) {


        //panel.add(this.wait);
        return new ClientMessageEndTurn();
    }




    //create elements

    private void displayPB(PlayerBoard pb){
        panel.remove(this.pbpanel);
        this.pbpanel = new PBPanel(pb);
        panel.add(this.pbpanel, pbc);
    }
    private void displayOPB(PlayerBoard pb){

    }
    private void displayMarket(Market m){
        //panel.add(new JLabel(m.view()));
    }
}
