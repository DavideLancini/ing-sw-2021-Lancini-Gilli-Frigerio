package it.polimi.ingsw.model;
/**
 * enum Level
 * @author Group 12
 */
public enum Level {
    ONE("."),
    TWO(":"),
    THREE("⋮");

    Level(String escape) { this.escape = escape; }

    private final String escape;

    @Override
    public String toString(){
        return escape;
    }
}
