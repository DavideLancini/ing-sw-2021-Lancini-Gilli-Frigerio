package it.polimi.ingsw.view.gui;

import javax.swing.*;
import java.awt.*;

public class StrdPannel {
public static GridBagConstraints gridBagConstraints;
    public static void main(String[] args) {
         int n;
        Object[] options = {"visualizza", "non visualizzare", "annulla"};
        {
            n = JOptionPane.showInternalOptionDialog(null, "sto provando", "tentare", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
        }

        if (n == JOptionPane.YES_OPTION) {
            new Frame();
        } else if (n==JOptionPane.NO_OPTION){
            System.out.println("Cliccato su " + options[1]);
        } else if (n == JOptionPane.CANCEL_OPTION) {
            System.out.println("Cliccato su " + options[2]);
        }
    }
}
