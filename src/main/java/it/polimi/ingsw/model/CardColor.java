package it.polimi.ingsw.model;

/**
 * enum CardColor
 * @author Gruppo 12
 */
public enum CardColor {
    BLUE("\u001b[38;5;12m⯀"),
    YELLOW("\u001b[38;5;11m⯀"),
    GREEN("\u001b[38;5;34m⯀"),
    PURPLE("\u001b[38;5;54m⯀");

    static final String RESET="\u001b[0m";

    private String s;

    CardColor(String s) {
        this.s= s;
    }

    @Override
    public String toString() {
        return s;
    }
}
