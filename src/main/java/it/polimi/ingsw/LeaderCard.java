package it.polimi.ingsw;

public class LeaderCard {
    private int VP;
    private boolean isActive;
    private Resource type;

    public int getVP(){
        return this.VP;
    }

    public Resource getType(){
        return this.type;
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public void toggleActive(){
        this.isActive = !this.isActive;
    }

    //TODO aggiungere le extension
}
