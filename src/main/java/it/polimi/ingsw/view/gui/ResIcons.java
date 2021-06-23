package it.polimi.ingsw.view.gui;

import javax.swing.*;

public enum ResIcons {
    STONE("src/main/resources/ResourceIcons/Stone.PNG"),
    SHIELD("src/main/resources/ResourceIcons/Shield.PNG"),
    COIN("src/main/resources/ResourceIcons/Coin.PNG"),
    SERVANT("src/main/resources/ResourceIcons/Servant.PNG"),
    EMPTY("src/main/resources/ResourceIcons/Empty.PNG");

    private String filename;

    ResIcons(String filename){this.filename = filename;}
    public ImageIcon toIcon(){return new ImageIcon(filename);}

}
