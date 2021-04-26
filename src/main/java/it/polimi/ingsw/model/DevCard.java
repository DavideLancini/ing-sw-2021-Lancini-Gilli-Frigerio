package it.polimi.ingsw.model;

public class DevCard {
    private Resource[] cost;
    private Level level;
    private CardColor color;
    private int VP;
    private Production production;

    public Resource[] getCost(){
        return this.cost;
    }

    public Level getLevel(){
        return this.level;
    }

    public int getVP(){
        return this.VP;
    }

    public Production getProduction() { return production; }

    public void initializeCard(){
        // forse serve, forse no
    }
}
