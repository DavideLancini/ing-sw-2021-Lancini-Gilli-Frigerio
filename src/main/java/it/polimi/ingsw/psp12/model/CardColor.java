package it.polimi.ingsw.psp12.model;

/**
 * enum CardColor
 * @author Group 12
 */
public enum CardColor {
    BLUE("\u001b[38;5;12m██\u001b[0m"),
    YELLOW("\u001b[38;5;11m██\u001b[0m"),
    GREEN("\u001b[38;5;34m██\u001b[0m"),
    PURPLE("\u001b[38;5;54m██\u001b[0m");

    //static final String RESET="\u001b[0m";

    private final String s;

    CardColor(String s) {
        this.s= s;
    }

    @Override
    public String toString() {
        return s;
    }
}
