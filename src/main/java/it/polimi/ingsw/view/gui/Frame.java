package it.polimi.ingsw.view.gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    ImageIcon image = new ImageIcon(
            getClass().getResource("/Masters of Renaissance_PlayerBoard (11_2020)-1.png"));
    Image scaledImg = image.getImage().getScaledInstance(500,320,Image.SCALE_REPLICATE);


    public Frame(){
        super("Masters Of Renaissance");
        setLayout(new FlowLayout());
        setSize(1920,1000);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
