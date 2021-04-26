package it.polimi.ingsw.model.singlePlayer;

public class DarkFaith {
    private static int darkFaith;

    public DarkFaith(){
        setDarkFaith(0);
    }
    static public  int getDarkFaith() {
        return darkFaith;
    }

   static public void setDarkFaith(int faith) {
        darkFaith = faith;
    }

}
