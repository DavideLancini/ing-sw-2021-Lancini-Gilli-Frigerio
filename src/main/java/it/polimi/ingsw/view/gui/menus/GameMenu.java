package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.DevCardBoard;
import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ClientMessageEndTurn;
import it.polimi.ingsw.network.messages.ServerMessageView;
import it.polimi.ingsw.view.gui.panels.DevBoardPanel;
import it.polimi.ingsw.view.gui.panels.MarketPanel;
import it.polimi.ingsw.view.gui.panels.PlayerBoardPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private final GridBagConstraints bc = new GridBagConstraints();

    private int playerNumber;
    private JPanel mpanel;
    private JPanel dbpanel;
    private JPanel buttonpanel;
    private String choice;
    private Market market;
    private DevCardBoard devCardBoard;
    private PlayerBoard playerBoard;
    private boolean first;

    //Buttons, order important
    private final JButton[] options = new JButton[]{
            new JButton("Take Resources from Market"),
            new JButton("Buy Development Card"),
            new JButton("Activate Productions"),
            new JButton("Leader Cards Options"),
            new JButton("Default Production Options"),
            new JButton("End Turn")
    };

    public GameMenu(){
        //order elements around

        JScrollPane sp = new JScrollPane(internalPanel);


        for(JButton each : this.options){
            each.addActionListener(this);
            each.setAlignmentX(Component.CENTER_ALIGNMENT);
            each.setMaximumSize(new Dimension(200, each.getMinimumSize().height));
        }

        wait.setFont(new Font(null, Font.PLAIN, 40));
        internalPanel.add(wait);
        setWaiting();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(sp);
        frame.setVisible(false);
        frame.setTitle("Masters of Renaissance");
        frame.setSize(1920,1080);
        internalPanel.setLayout(new GridBagLayout());
        internalPanel.setBorder(new EmptyBorder(new Insets(20,20,30,30)));



        //Constraints setup
        pbc.gridy = 1;
        pbc.gridx = 2;
        pbc.gridheight = 4;
        mc.gridy = 4;
        mc.gridx = 0;
        mc.weightx = 0;
        mc.weighty = 0;
        mc.gridheight = 1;
        dbc.gridy = 2;
        dbc.gridx = 1;
        dbc.gridheight = pbc.gridheight-1;
        dbc.weightx = 0.5;
        pbc.weightx = 0.5;
        bc.gridy = mc.gridy-2;
        bc.gridx = 0;

    }

    public void setVisible(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public void close(){
        frame.dispose();
    }

    public void setWaiting(){
        //TODO: fix glassPane
        hideButtons();
    }

    private void displayFirst(){
        internalPanel.remove(wait);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = mc.gridx;
        c.gridy = mc.gridy - 1;
        c.gridheight = 2;
        internalPanel.add(new ButtonPanel(false), c);

        c.gridheight = 1;
        c.gridx = mc.gridx;
        c.gridy = mc.gridy-1;
        JLabel label = new JLabel("Market");
        label.setFont(new Font(null, Font.BOLD,15));
        internalPanel.add(label, c);

        c.gridx = dbc.gridx;
        c.gridy = dbc.gridy-1;
        label = new JLabel("Development Cards Board");
        label.setFont(new Font(null, Font.BOLD,15));
        internalPanel.add(label, c);
    }


    public void display(ServerMessageView opanel) {
        if(!first)displayFirst();
        first = true;
        //activate elements
        switch(opanel.getElem()){
            case PB: displayPB(Serializer.deserializePB(opanel.getView(false))); break;
            case OtherPB:
                displayOPB(Serializer.deserializePB(opanel.getView(false)),
                        opanel.getView(true).split("\n",2)[0]);
                break;
            case Market: displayMarket(Serializer.deserializeMarket(opanel.getView(false))); break;
            case DevBoard: displayDev(Serializer.deserializeDB(opanel.getView(false))); break;
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }


    //interactions

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton option : this.options) {
            if (option.equals(e.getSource())) {
                synchronized (this) {
                    this.choice = e.getActionCommand();
                    this.notify();
                }
            }
        }
    }



    public ClientMessage turn(boolean action) {
        showButtons(action);

        synchronized (this){
            while(this.choice == null || this.choice.equals("")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ClientMessage message = null;
        while(message == null) {
            switch (choice) {
                case "Take Resources from Market":
                    message = new TakeResourcesMenu(this.market).prompt();
                    break;
                case "Buy Development Card":
                    message = new BuyDevCardMenu(this.devCardBoard.getTop()).prompt();
                    break;
                case "Activate Productions":
                    message = new ProduceMenu(this.playerBoard).prompt();
                    break;
                case "Leader Cards Options":
                    message = new LeaderOptionsMenu(this.playerBoard.getLeaderCard()).prompt();
                    break;
                case "Default Production Options":
                    message = new DefaultProductionMenu(this.playerBoard.getDefaultProduction()).prompt();
                    break;
                case "End Turn":
                    message = new ClientMessageEndTurn();
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
        choice = null;
        hideButtons();
        return message;
    }

    private void showButtons(boolean action) {
        buttonpanel = new ButtonPanel(action);
        internalPanel.add(buttonpanel, bc);
        SwingUtilities.updateComponentTreeUI(frame);
    }

    private void hideButtons(){
        try{
            internalPanel.remove(buttonpanel);
        }
        catch (NullPointerException ignored){}
        internalPanel.add(Box.createRigidArea(new Dimension(10,10)),bc);
        SwingUtilities.updateComponentTreeUI(frame);
    }


    //create elements

    private void displayPB(PlayerBoard pb){
        try{
            internalPanel.remove(pbpanel);
        }
        catch (NullPointerException ignored){}
        this.pbpanel = new PlayerBoardPanel(pb,true, "YOU");
        this.playerBoard = pb;
        internalPanel.add(this.pbpanel, pbc);
    }

    private void displayOPB(PlayerBoard pb, String id){
        try{
            internalPanel.remove(others[playerNumber]);
        }
        catch (NullPointerException ignored){}

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 10;
        c.gridx = playerNumber;
        c.weightx = 1;
        c.insets = new Insets(0,5,15,5);
        others[playerNumber] = new PlayerBoardPanel(pb, false, id);
        internalPanel.add(others[playerNumber], c);
        playerNumber++;
    }

    private void displayMarket(Market m){
        try{
            internalPanel.remove(this.mpanel);
        }
        catch (NullPointerException ignored){}
        this.mpanel = new MarketPanel(m);
        this.market = m;
        internalPanel.add(this.mpanel, mc);

    }

    private void displayDev(DevCardBoard db){
        this.playerNumber = 0;
        try{
            internalPanel.remove(this.dbpanel);
        }
        catch (NullPointerException ignored){}
        this.dbpanel = new DevBoardPanel(db);
        this.devCardBoard = db;
        internalPanel.add(this.dbpanel, dbc);
    }

    private class ButtonPanel extends JPanel{
        ButtonPanel(boolean action){
            super();
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            for(int i = 0; i<3 && !action; i++){
                this.add(options[i]);
                this.add(Box.createRigidArea(new Dimension(0,5)));
            }
            for(int i = 3; i<options.length; i++){
                this.add(options[i]);
                this.add(Box.createRigidArea(new Dimension(0,5)));
            }
        }

    }
}

