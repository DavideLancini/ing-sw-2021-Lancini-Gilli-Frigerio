package it.polimi.ingsw;

public class LeaderCard {
    private int VP;
    private boolean isActive;
    private Resource type;

    public int getVP(){
        return this.VP;
    }

    public int getType(){
        return this.type;
    }

    public int getIsActive(){
        return this.isActive;
    }

    public void toggleActive(){
        if(this.isActive) {
            this.isActive = false;
        } else this.isActive = true;
    }

    //TODO aggiungere le extension
}
