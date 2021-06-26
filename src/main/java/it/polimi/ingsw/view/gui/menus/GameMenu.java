package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageEndTurn;
import it.polimi.ingsw.network.messages.ServerMessageView;
import it.polimi.ingsw.view.gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu implements ActionListener {
    private final JFrame frame = new JFrame();
    private final JPanel internalPanel = new JPanel();
    private final JLabel wait = new JLabel("Waiting for the other players...");
    private PlayerBoardPanel pbpanel;
    private final PlayerBoardPanel[] others = new PlayerBoardPanel[3];


    private final GridBagConstraints pbc = new GridBagConstraints();
    private final GridBagConstraints mc = new GridBagConstraints();
    private final GridBagConstraints dbc = new GridBagConstraints();
    private int playerNumber;
    private JPanel mpanel;
    private JPanel dbpanel;

    public GameMenu(){
        //order elements around
        wait.setFont(new Font(null, Font.PLAIN, 40));
        setWaiting();
        JScrollPane sp = new JScrollPane(internalPanel);

        frame.getContentPane().add(sp);
        frame.setVisible(false);
        frame.setTitle("Masters of Renaissance");
        frame.setSize(1920,1080);
        internalPanel.setLayout(new GridBagLayout());
        pbc.gridy = 1;
        pbc.gridx = 2;
        mc.gridy = 1;
        mc.gridx = 0;
        mc.weightx = 0;


        dbc.gridy = 1;
        dbc.gridx = 1;
        dbc.weightx = 0.5;
        pbc.weightx = 0.5;
    }

    public void setVisible(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public void setWaiting(){
        //TODO
    }


    public void display(ServerMessageView opanel) {
        //activate elements
        internalPanel.remove(wait);

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
        //TODO
    }

    //interactions

    public ClientMessage turn(boolean action) {


        //panel.add(this.wait);
        return new ClientMessageEndTurn();
    }




    //create elements

    private void displayPB(PlayerBoard pb){
        try{
            internalPanel.remove(pbpanel);
        }
        catch (NullPointerException ignored){}
        this.pbpanel = new PlayerBoardPanel(pb,true);
        internalPanel.add(this.pbpanel, pbc);
    }

    private void displayOPB(PlayerBoard pb){
        try{
            internalPanel.remove(others[playerNumber]);
        }
        catch (NullPointerException ignored){}

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = playerNumber;
        c.weightx = 1;
        others[playerNumber] = new PlayerBoardPanel(pb, false);
        internalPanel.add(others[playerNumber], c);
        playerNumber++;
    }

    private void displayMarket(Market m){
        try{
            internalPanel.remove(this.mpanel);
        }
        catch (NullPointerException ignored){}
        this.mpanel = new MarketPanel(m);
        internalPanel.add(this.mpanel, mc);
    }

    private void displayDev(DevCardBoard db){
        this.playerNumber = 0;
        try{
            internalPanel.remove(this.dbpanel);
        }
        catch (NullPointerException ignored){}
        this.dbpanel = new DevBoardPanel(db);
        internalPanel.add(this.dbpanel, dbc);
    }
}
