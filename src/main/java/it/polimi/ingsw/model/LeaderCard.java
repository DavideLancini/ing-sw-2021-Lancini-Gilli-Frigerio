package it.polimi.ingsw.model;
/**
 * Class LeaderCard
 * @author Gruppo 12
 */
public abstract class LeaderCard {


    private Resource[] require;
    private int VP;
    private boolean isActive = false;
    private Resource type;
    private Resource choice= Resource.EMPTY;
    private Production production;

    public void setProduction(Production production) {
        this.production = production;
    }

    public Production getProduction() {
        return this.production;
    }
    /**
     * setChoice
     *
     * @param choice resource player wants to produce
     */
    public void setChoice(Resource choice) {
        this.choice = choice;
    }

    /**
     *
     * @return choice
     */
    public Resource getChoice() {
        return choice;
    }
    /**
     * get  requirements
     * @return  requirements to active Leader card
     */
    public Resource[] getRequire() {
        return require;
    }

    /**
     * Set requirements
     * @param require requirements to active Leader card
     */
    public void setRequire(Resource[] require) {
        this.require = require;
    }

    /**
     * setVP
     *
     * @param VP initialize victory points
     */
    public void setVP(int VP) {
        this.VP = VP;
    }

    /**
     * getVP
     *
     * @return victory points
     */
    public int getVP() {
        return this.VP;
    }

    /**
     * setType
     *
     * @param type resource LeaderCard works with
     */
    public void setType(Resource type) {
        this.type = type;
    }

    /**
     * getType
     *
     * @return resource LeaderCard works with
     */
    public Resource getType() {
        return this.type;
    }

    /**
     * getIsActive
     *
     * @return isActive status
     */
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * toggleActive
     * modify isActive status
     */
    public void toggleActive() {
        this.isActive = !this.isActive;
    }

    public Resource WhiteTo(Marble white) throws Exception {
        return null;
    }

    public void DownPrice(Resource[] cost){}

    public void activateDepot(Depot depot){}

    /**
     * LeaderCard view
     */
    public void leaderCardView() {
        System.out.print("Requires[");
        new ResourceCounter(this.getRequire());
        System.out.println("]");
        System.out.println("\u001b[38:5:221m {"+this.getVP()+"}\u001b[m");
    }
}
/**
 * Class LeaderSale extends LeaderCard
 * @author Gruppo 12
 */
class LeaderSale extends LeaderCard{
    public LeaderSale(int vp,Resource type,Resource[] require) {
        this.setVP(vp);
        this.setType(type);
        this.setRequire(require);
    }
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
    /**
     * LeaderCard view
     */
    public void leaderCardView() {
        System.out.print("Requires[");
        new ResourceCounter(this.getRequire());
        System.out.println("]");
        System.out.println(" -"+this.getType());
        System.out.println("\u001b[38:5:221m {"+this.getVP()+"}\u001b[m");
    }
}
/**
 * Class LeaderDepot extends LeaderCard
 * @author Gruppo 12
 */
class LeaderDepot extends LeaderCard{
    public LeaderDepot(int vp,Resource type,Resource[] require) {
        this.setVP(vp);
        this.setType(type);
        this.setRequire(require);
    }
    /**
     * activateDepot
     * @param depot player personal depot
     */
    public void activateDepot(Depot depot){
        depot.activateLeader(getType());
    }

    public void leaderCardView() {
        System.out.print("Requires[");
        new ResourceCounter(this.getRequire());
        System.out.println("]");
        System.out.println(getType()+""+getType());
        System.out.println("\u001b[38:5:221m {"+this.getVP()+"}\u001b[m");
    }
}
