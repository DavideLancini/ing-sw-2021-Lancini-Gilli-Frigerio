package it.polimi.ingsw.psp12.model;
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
