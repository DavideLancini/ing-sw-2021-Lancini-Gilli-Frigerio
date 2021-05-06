package it.polimi.ingsw.model;
/**
 * Class DevCard
 * @author Gruppo 12
 */
public class DevCard {
    private Resource[] cost;
    private Level level;
    private CardColor color;
    private int VP;
    private Production production;

    /**
     * getCost
     * @return price,in resources, to buy this devCard
     */
    public Resource[] getCost(){
        return this.cost;
    }

    /**
     * getLevel
     * @return level of devCard
     */
    public Level getLevel(){
        return this.level;
    }

    /**
     * getVP
     * @return victory points
     */
    public int getVP(){
        return this.VP;
    }

    /**
     * getProduction
     * @return Production for this devCard
     */
    public Production getProduction() { return production; }

    /**
     * class constructor
     * @param level level of devCard
     * @param color color of devCard
     * @param VP victory points of devCard
     * @param cost cost of devCard
     * @param production production of devCard
     */
    public DevCard(Level level, CardColor color, int VP, Resource[] cost, Production production){
        this.cost = cost;
        this.level = level;
        this.color = color;
        this.VP = VP;
        this.production = production;
    }
}
