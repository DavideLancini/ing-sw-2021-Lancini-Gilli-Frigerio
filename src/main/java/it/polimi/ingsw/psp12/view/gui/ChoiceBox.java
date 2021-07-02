package it.polimi.ingsw.psp12.view.gui;

import javax.swing.*;

public class ChoiceBox {

    public static int prompt(String message, String title, String[] options) {
        return JOptionPane.showOptionDialog(
                null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
    }

    public static int prompt(String message, String title, ImageIcon[] options) {
        return JOptionPane.showOptionDialog(
                null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
    }

}
