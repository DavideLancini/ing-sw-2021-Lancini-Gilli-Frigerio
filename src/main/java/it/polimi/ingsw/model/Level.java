package it.polimi.ingsw.model;
/**
 * enum Level
 * @author Gruppo 12
 */
public enum Level {
    ONE("."),
    TWO(":"),
    THREE("⋮");

    Level(String escape) { this.escape = escape; }

    private String escape;

    @Override
    public String toString(){
        return escape;
    }
}
