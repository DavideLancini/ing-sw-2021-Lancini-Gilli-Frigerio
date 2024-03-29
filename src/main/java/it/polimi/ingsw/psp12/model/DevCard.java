package it.polimi.ingsw.psp12.model;

/**
 * Class DevCard
 * @author Group 12
 */
public class DevCard {
    private final Resource[] cost;
    private final Level level;
    private final CardColor color;
    private final int VP;
    private final Production production;
    private final String imagePath;

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
     * getColor
     * @return color of devCard
     */
    public CardColor getColor(){
        return this.color;
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
    public DevCard(Level level, CardColor color, int VP, Resource[] cost, Production production,String imagePath){
        this.cost = cost;
        this.level = level;
        this.color = color;
        this.VP = VP;
        this.production = production;
        this.imagePath=imagePath;
    }

    /**
     * devCardView
     * @return string to show
     */
    public String devCardView(){
        String string="";
        string=string.concat("Price["+ResourceCounter.count(this.cost)+"]");
        for(int i = 0; i<4-ResourceCounter.countTypes(this.cost); i++){string = string.concat("   ");}

        string=string.concat("\n"+this.production.view());
        string=string.concat(this.level+" "+this.color+"\u001b[38:5:221m {"+this.VP+"}\u001b[m        \n");

        return string;
    }
    /**
     * devCardView for covered cards(lower level)
     * @return string to show
     */
    public String coveredView(){
        String string="";
        string=string.concat("━━━━━━━━━━━━━━━━━━\n");
        string=string.concat("  "+this.level+" "+this.color+"\u001b[38:5:221m {"+this.VP+"}\u001b[m\n");// covered card possible view
        return string;
    }
    /**
    * @return path of associated image
    */
    public String getPath() {
        return imagePath;
    }
}
