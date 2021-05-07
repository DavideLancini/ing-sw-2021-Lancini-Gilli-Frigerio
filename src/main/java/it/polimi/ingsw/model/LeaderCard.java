package it.polimi.ingsw.model;
/**
 * Class LeaderCard
 * @author Gruppo 12
 */
public abstract class LeaderCard {
    private int VP;
    private boolean isActive= false;
    private Resource type;

    /**
     * setVP
     * @param VP initialize victory points
     */
    public void setVP(int VP) {
        this.VP = VP;
    }

    /**
     * getVP
     * @return victory points
     */
    public int getVP(){
        return this.VP;
    }

    /**
     * setType
     * @param type resource LeaderCard works with
     */
    public void setType(Resource type) {
        this.type = type;
    }

    /**
     * getType
     * @return resource LeaderCard works with
     */
    public Resource getType(){
        return this.type;
    }

    /**
     * getIsActive
     * @return isActive status
     */
    public boolean getIsActive(){
        return this.isActive;
    }

    /**
     * toggleActive
     * modify isActive status
     */
    public void toggleActive(){
        this.isActive = !this.isActive;
    }


}
/**
 * Class LeaderSale extends LeaderCard
 * @author Gruppo 12
 */
class LeaderSale extends LeaderCard{
    /**
     * DownPrice
     * remove 1 resource based on leader type from any devCard cost
     * @param cost devCard cost
     */
    public void DownPrice(Resource[] cost){
        var i = 0;
        var done = false;
        //remove 1 resource based on type
        while(cost[i]!=null && !done) {
            if (cost[i].equals(getType())) {
                cost[i] = null;
                done = true;
            }
            i++;
        }
    }
}
/**
 * Class LeaderDepot extends LeaderCard
 * @author Gruppo 12
 */
class LeaderDepot extends LeaderCard{
    /**
     * activateDepot
     * @param depot player personal depot
     */
    public void activateDepot(Depot depot){
        depot.activateLeader(getType());
    }
}
/**
 * Class LeaderProduction extends LeaderCard
 * @author Gruppo 12
 */
class LeaderProduction extends LeaderCard{
    public Production production;
    private Resource choice;

    /**
     * setChoice
     * @param choice resource player wants to produce
     */
    public void setChoice(Resource choice) {
        this.choice = choice;
    }
}
