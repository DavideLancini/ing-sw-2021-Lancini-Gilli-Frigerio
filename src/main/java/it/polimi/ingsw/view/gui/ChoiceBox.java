package it.polimi.ingsw.view.gui;

import javax.swing.*;
import java.awt.*;

public class ChoiceBox {

    public static int prompt(String message, String title, String[] options) {
        return JOptionPane.showOptionDialog(
                null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
}
