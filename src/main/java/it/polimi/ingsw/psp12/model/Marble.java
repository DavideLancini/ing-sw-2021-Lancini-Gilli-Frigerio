package it.polimi.ingsw.psp12.model;
/**
 * enum Marble
 * @author Group 12
 */
public enum Marble {
    WHITE("\u001b[38;5;15m⏺\u001b[0m"),
    BLUE("\u001b[38;5;12m⏺\u001b[0m"),
    GRAY("\u001b[38;5;8m⏺\u001b[0m"),
    YELLOW("\u001b[38;5;11m⏺\u001b[0m"),
    PURPLE("\u001b[38;5;54m⏺\u001b[0m"),
    RED("\u001b[38;5;9m⏺\u001b[0m");

    static final String RESET="\u001b[0m";

    Marble(String escape) { this.escape = escape; }

    private final String escape;

    @Override
    public String toString(){
        return escape;
   }

}
