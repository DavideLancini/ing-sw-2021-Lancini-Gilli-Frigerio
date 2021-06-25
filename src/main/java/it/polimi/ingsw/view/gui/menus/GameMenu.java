package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageEndTurn;
import it.polimi.ingsw.network.messages.ServerMessageView;
import it.polimi.ingsw.view.gui.playerboardPanels.PBPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu implements ActionListener {
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final JLabel wait = new JLabel("Waiting for your turn...");
    private PBPanel pbpanel = new PBPanel();
    private final PBPanel[] others = new PBPanel[3];


    private final GridBagConstraints pbc = new GridBagConstraints();
    private int playerNumber;

    public GameMenu(){
        //order elements around
        panel.add(this.wait);
        JScrollPane sp = new JScrollPane(panel);

        frame.getContentPane().add(sp);
        frame.setVisible(false);
        frame.setTitle("Masters of Renaissance");
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
            case DevBoard: displayDev(Serializer.deserializeDB(opanel.getView(false))); break;
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
        try{
            panel.remove(pbpanel);
        }
        catch (NullPointerException ignored){}
        this.pbpanel = new PBPanel(pb,true);
        panel.add(this.pbpanel, pbc);
    }
    private void displayOPB(PlayerBoard pb){
        try{
            panel.remove(others[playerNumber]);
        }
        catch (NullPointerException ignored){}

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = playerNumber;
        c.weightx = 1;
        others[playerNumber] = new PBPanel(pb, false);
        panel.add(others[playerNumber], c);
        playerNumber++;
    }
    private void displayMarket(Market m){
        //panel.add(new JLabel(m.view()));
    }
    private void displayDev(DevCardBoard db){
        this.playerNumber = 0;
    }
}
