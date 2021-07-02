package it.polimi.ingsw.psp12.model;

import it.polimi.ingsw.psp12.view.gui.ResIcons;

import javax.swing.*;

/**
 * enum Resource
 * @author Group 12
 */
public enum Resource {
    STONE("\u001b[38;5;246m⛘\u001b[0m"),
    SERVANT("\u001b[38;5;54m\uD83D\uDC64\u001b[0m"),
    COIN("\u001b[38;5;11m⭖\u001b[0m"),
    SHIELD("\u001b[38;5;12m⛊\u001b[0m"),
    EMPTY("⊗"),
    FAITH("\u001b[38;5;9m\uD83D\uDD47\u001b[0m");

    Resource(String escape) { this.escape = escape; }

    private final String escape;

    @Override
    public String toString(){
        return escape;
    }

    public ImageIcon toIcon(){
        return ResIcons.values()[this.ordinal()].toIcon();
    }

}

//RESET="\u001b[0m";