package it.polimi.ingsw.model;
/**
 * enum Marble
 * @author Gruppo 12
 */
public enum Marble {
    WHITE("\u001b[38;5;15m⏺"),
    BLUE("\u001b[38;5;12m⏺"),
    GRAY("\u001b[38;5;8m⏺"),
    YELLOW("\u001b[38;5;11m⏺"),
    PURPLE("\u001b[38;5;54m⏺"),
    RED("\u001b[38;5;9m⏺");

    static final String RESET="\u001b[0m";

    Marble(String escape) { this.escape = escape; }

    private final String escape;

    @Override
    public String toString(){
        return escape;
   }

}
