package it.polimi.ingsw.view.gui;

import javax.swing.*;
import java.awt.*;

public enum ResIcons {
    STONE("src/main/resources/Icons/Stone.PNG"),
    SHIELD("src/main/resources/Icons/Shield.PNG"),
    COIN("src/main/resources/Icons/Coin.PNG"),
    SERVANT("src/main/resources/Icons/Servant.PNG"),
    EMPTY("src/main/resources/Icons/Empty.PNG"),
    FAITH("src/main/resources/Icons/Faith.PNG");
    private final String filename;

    ResIcons(String filename){this.filename = filename;}
    public ImageIcon toIcon(){return new ImageIcon(filename);}
    public ImageIcon toIcon(int x, int y){return new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));}
}
