package it.polimi.ingsw;

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

    /*   public Resource[] getInputProduction(){
        //TODO manca la classe production
    }

    public Resource[] getOutputProduction(){
        //TODO manca la classe production
    }
    */

    public void initializeCard(){
        // forse serve, forse no
    }
}
