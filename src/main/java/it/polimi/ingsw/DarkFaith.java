package it.polimi.ingsw;

public class DarkFaith {
    private static int darkFaith=0;

    static synchronized public  int getDarkFaith() {
        return darkFaith;
    }

   static synchronized public void setDarkFaith(int faith) {
        darkFaith = faith;
    }

}
