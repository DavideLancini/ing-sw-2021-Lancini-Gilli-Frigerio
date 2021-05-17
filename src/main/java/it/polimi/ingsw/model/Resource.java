package it.polimi.ingsw.model;
/**
 * enum Resource
 * @author Gruppo 12
 */
public enum Resource {
    STONE("\u001b[38;5;246m⛘\u001b[0m"),
    SERVANT("\u001b[38;5;54m\uD83D\uDC64\u001b[0m"),
    COIN("\u001b[38;5;11m⭖\u001b[0m"),
    SHIELD("\u001b[38;5;12m⛊\u001b[0m"),
    EMPTY("⊗"),
    FAITH("\u001b[38;5;9m\uD83D\uDD47\u001b[0m");
    static final String RESET="\u001b[0m";

    Resource(String escape) { this.escape = escape; }

    private String escape;

    @Override
    public String toString(){
        return escape;
    }

}
