package it.polimi.ingsw.model;

/**
 * enum Pope
 */
public enum Pope {
    DONE(99,99,0), FIRST(8,5,2), SECOND(16,12,3), THIRD(24,19,4);
    public final int value;
    public final int min;
    public int vp;

    Pope(int value, int min, int vp){this.value = value; this.min = min; this.vp = vp;}

}
