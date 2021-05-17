package it.polimi.ingsw.model;

import java.util.Arrays;
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

    /**
     * devCardView
     */
    public String devCardView(){
        System.out.print("Price[");
        new ResourceCounter(this.cost);
        System.out.println("]");
        this.production.productionView();
        System.out.println("  "+this.level+" "+this.color+"\u001b[38:5:221m {"+this.VP+"}\u001b[m");// covered card possible view
        return "";
    }
    @Override
    public String toString(){
        return devCardView();
    }
}
